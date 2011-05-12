package dialogue;

import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class fenStatus extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JButton jBipOk = null;
	private JButton jBPasserelleOK = null;
	private JButton jBDNS1Ok = null;
	private JButton jBDNS2Ok = null;
	private JLabel jLPaserelle = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;

	/**
	 * This is the default constructor
	 */
	public fenStatus() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * @return void
	 */
	private void initialize() {
		this.setSize(374, 328);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/LogoPrincipal.png")));
		this.setContentPane(getJContentPane());
		this.setTitle("Status");
		this.setVisible(true);
		this.setLocationRelativeTo(null); // On centre la fenêtre sur l'écran

	}

	/**
	 * This method initializes jContentPane
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(36, 241, 170, 29));
			jLabel2.setText("Configuration DNS2");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(36, 171, 170, 29));
			jLabel1.setText("Configuration DNS1");
			jLPaserelle = new JLabel();
			jLPaserelle.setBounds(new Rectangle(36, 101, 170, 29));
			jLPaserelle.setText("Configuration passerelle");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(36, 31, 170, 29));
			jLabel.setText(" Configuration IP et masque");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(getJBipOk(), null);
			jContentPane.add(getJBPasserelleOK(), null);
			jContentPane.add(getJBDNS1Ok(), null);
			jContentPane.add(getJBDNS2Ok(), null);
			jContentPane.add(jLPaserelle, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jBipOk
	 * @return javax.swing.JButton
	 */
	public JButton getJBipOk() {
		if (jBipOk == null) {
			jBipOk = new JButton();
			jBipOk.setBounds(new Rectangle(244, 20, 50, 50));
			jBipOk.setIcon(new ImageIcon(getClass().getResource(
					"/testbal/ok.png")));
			jBipOk.setVisible(false);
		}
		return jBipOk;
	}

	/**
	 * This method initializes jBPasserelleOK
	 * @return javax.swing.JButton
	 */
	public JButton getJBPasserelleOK() {
		if (jBPasserelleOK == null) {
			jBPasserelleOK = new JButton();
			jBPasserelleOK.setBounds(new Rectangle(244, 90, 50, 50));
			jBPasserelleOK.setIcon(new ImageIcon(getClass().getResource(
					"/testbal/ok.png")));
			jBPasserelleOK.setVisible(false);
		}
		return jBPasserelleOK;
	}

	/**
	 * This method initializes jBDNS1Ok
	 * @return javax.swing.JButton
	 */
	public JButton getJBDNS1Ok() {
		if (jBDNS1Ok == null) {
			jBDNS1Ok = new JButton();
			jBDNS1Ok.setBounds(new Rectangle(244, 160, 50, 50));
			jBDNS1Ok.setIcon(new ImageIcon(getClass().getResource(
					"/testbal/ok.png")));
			jBDNS1Ok.setVisible(false);
		}
		return jBDNS1Ok;
	}

	/**
	 * This method initializes jBDNS2Ok
	 * @return javax.swing.JButton
	 */
	public JButton getJBDNS2Ok() {
		if (jBDNS2Ok == null) {
			jBDNS2Ok = new JButton();
			jBDNS2Ok.setBounds(new Rectangle(244, 230, 50, 50));
			jBDNS2Ok.setIcon(new ImageIcon(getClass().getResource(
					"/testbal/ok.png")));
			jBDNS2Ok.setVisible(false);
		}
		return jBDNS2Ok;
	}

} // @jve:decl-index=0:visual-constraint="10,10"
