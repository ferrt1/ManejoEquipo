package interfaz;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingWorker;

import equipo.ManejoEquipo;
import equipo.Solver;

import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JPanel;
import personas.*;

public class Simulacion extends SwingWorker<List<Persona>, Persona> {
	
	private JPanel panelResultados;  // Cambiamos JTextField por JPanel
	private JProgressBar barraProgreso;
	private ManejoEquipo manejoEquipo;
	private Color colorBotones = new Color(75, 105, 131);
	private Font fuente = new Font("Segoe UI", Font.BOLD, 15);
	
	public Simulacion(JPanel panel, JProgressBar barra, int numero, ManejoEquipo manejoEquipo) {
		this.panelResultados = panel;  
		this.barraProgreso = barra;
		this.manejoEquipo = manejoEquipo;
	}

	@Override
	public List<Persona> doInBackground() throws Exception{
		// Procedimiento costoso
		barraProgreso.setIndeterminate(true);

		Solver solve = new Solver(manejoEquipo);
		solve.start();
		solve.join();
		
		return solve.getSolucion();
	}
	
	@Override
	public void done() {
	    try {
	        if (!this.isCancelled()) {
	            List<Persona> personas = get();
	            GridBagConstraints gbc = new GridBagConstraints();
	            gbc.gridwidth = GridBagConstraints.REMAINDER;
	            gbc.fill = GridBagConstraints.HORIZONTAL;

	            if(personas.size() == 0) {
	                JLabel labelNoSolucion = new JLabel("No se encontro solucion", SwingConstants.CENTER);
	                labelNoSolucion.setOpaque(true);
	                labelNoSolucion.setBackground(colorBotones); 
	                labelNoSolucion.setForeground(Color.WHITE);
	                panelResultados.add(labelNoSolucion, gbc);  
	            }
	            for (Persona persona : personas) {
	                JLabel labelPersona = new JLabel(persona.getNombre() + " - " + persona.getRol(), SwingConstants.CENTER);
	                labelPersona.setOpaque(true);
	                labelPersona.setBackground(colorBotones);  
	                labelPersona.setForeground(Color.WHITE);
	                labelPersona.setFont(fuente);
	                panelResultados.add(labelPersona, gbc);  
	            }

	            panelResultados.revalidate();

	            barraProgreso.setIndeterminate(false);
	        }
	    } catch (InterruptedException ex) {
	        JLabel labelError = new JLabel("Interrumpido mientras esperaba resultados", SwingConstants.CENTER);
	        labelError.setOpaque(true);
	        labelError.setBackground(Color.RED);
	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.gridwidth = GridBagConstraints.REMAINDER;
	        gbc.fill = GridBagConstraints.HORIZONTAL;
	        panelResultados.add(labelError, gbc);
	    } catch (ExecutionException e) {
	        e.printStackTrace();
	    }
	}
}