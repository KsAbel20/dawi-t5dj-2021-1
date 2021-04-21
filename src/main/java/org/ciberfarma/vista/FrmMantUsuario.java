package org.ciberfarma.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.ciberfarma.modelo.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmMantUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtcodigo;
	private JTextField txtNom;
	private JTextField txtApe;
	private JTextField txtUsu;
	private JTextField txtClave;
	private JTextField txtFecha;
	private JTextField txtTipo;
	private JTextField txtEstado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMantUsuario frame = new FrmMantUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmMantUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mantenimiento de Usuarios");
		lblNewLabel.setBounds(46, 39, 150, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(46, 74, 42, 24);
		contentPane.add(lblCodigo);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(46, 109, 42, 24);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido ");
		lblApellido.setBounds(46, 144, 42, 24);
		contentPane.add(lblApellido);
		
		JLabel lblUsuario = new JLabel("Usuario: ");
		lblUsuario.setBounds(46, 179, 60, 24);
		contentPane.add(lblUsuario);
		
		JLabel lblClave = new JLabel("Clave");
		lblClave.setBounds(46, 214, 42, 24);
		contentPane.add(lblClave);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(46, 249, 42, 24);
		contentPane.add(lblFecha);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(46, 319, 42, 24);
		contentPane.add(lblEstado);
		
		JLabel lblTipo = new JLabel("Tipo ");
		lblTipo.setBounds(46, 284, 42, 24);
		contentPane.add(lblTipo);
		
		txtcodigo = new JTextField();
		txtcodigo.setBounds(98, 76, 121, 20);
		contentPane.add(txtcodigo);
		txtcodigo.setColumns(10);
		
		txtNom = new JTextField();
		txtNom.setBounds(98, 111, 121, 20);
		contentPane.add(txtNom);
		txtNom.setColumns(10);
		
		txtApe = new JTextField();
		txtApe.setBounds(98, 146, 121, 20);
		contentPane.add(txtApe);
		txtApe.setColumns(10);
		
		txtUsu = new JTextField();
		txtUsu.setBounds(98, 181, 121, 20);
		contentPane.add(txtUsu);
		txtUsu.setColumns(10);
		
		txtClave = new JTextField();
		txtClave.setBounds(98, 216, 121, 20);
		contentPane.add(txtClave);
		txtClave.setColumns(10);
		
		txtFecha = new JTextField();
		txtFecha.setBounds(98, 251, 121, 20);
		contentPane.add(txtFecha);
		txtFecha.setColumns(10);
		
		txtTipo = new JTextField();
		txtTipo.setBounds(98, 286, 121, 20);
		contentPane.add(txtTipo);
		txtTipo.setColumns(10);
		
		txtEstado = new JTextField();
		txtEstado.setBounds(98, 321, 121, 20);
		contentPane.add(txtEstado);
		txtEstado.setColumns(10);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrar();
			}
		});
		btnRegistrar.setBounds(255, 145, 89, 23);
		contentPane.add(btnRegistrar);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(255, 180, 89, 23);
		contentPane.add(btnActualizar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(255, 215, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnconsultar = new JButton("Consultar");
		btnconsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultar();
			}
		});
		btnconsultar.setBounds(255, 250, 89, 23);
		contentPane.add(btnconsultar);
	}
	
	void consultar() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		
		EntityManager em = fabrica.createEntityManager();
		
		Usuario u = em.find(Usuario.class,Integer.parseInt(txtcodigo.getText()));
		if(u !=null) {
			txtNom.setText(u.getNombre());
			txtApe.setText(u.getApellido());
			txtUsu.setText(u.getUsuario());
			txtClave.setText(u.getClave());
			txtFecha.setText(u.getFnacim());
			
			
		}else {
			JOptionPane.showMessageDialog(this,"usuario con codigo no existe");
		}
		
		em.close();
		fabrica.close();
	}
	
	
	void registrar() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		
		EntityManager em = fabrica.createEntityManager();
		
		Usuario u = new Usuario();
		//u.setCodigo(6);
		u.setNombre(txtNom.getText());
		u.setApellido(txtApe.getText());
		u.setUsuario(txtUsu.getText());
		u.setClave(txtClave.getText());
		u.setFnacim(txtFecha.getText());
		u.setTipo(Integer.parseInt(txtTipo.getText()));
		u.setEstado(Integer.parseInt(txtEstado.getText()));
		
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
		
		em.close();
		fabrica.close();
	}
	
}
