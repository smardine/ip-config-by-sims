package thread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dialogue.ReadFile;
import dialogue.fenStatus;

public class thread_applique extends Thread {

	private JComboBox comboBox;
	private JTextField ip, subMask, passerelle, dns1, dns2;
	private JLabel status;
	private JButton BtIp, BtPasserelle, BtDns1, BtDns2;
	private boolean dns2Applique;
	private boolean dns1Applique;
	private boolean passerelleApplique;
	private boolean ipApplique;

	public thread_applique(JLabel p_status, JComboBox p_comboBox,
			JTextField p_ip, JTextField p_subMask, JTextField p_passerelle,
			JTextField p_dns1, JTextField p_dns2) {
		status = p_status;
		comboBox = p_comboBox;
		ip = p_ip;
		subMask = p_subMask;
		passerelle = p_passerelle;
		dns1 = p_dns1;
		dns2 = p_dns2;

	}

	public void run() {
		/**
		 * declaration des bouton de status sur la fenetre de status
		 */
		fenStatus fen = new fenStatus();
		BtIp = fen.getJBipOk();
		BtPasserelle = fen.getJBPasserelleOK();
		BtDns1 = fen.getJBDNS1Ok();
		BtDns2 = fen.getJBDNS2Ok();

		List<String> lstCmd = new ArrayList<String>();
		String cmdEnvoi = String
				.format(
						"cmd.exe /c netsh interface ip set address name=\"%s\" source=static addr=%s mask=%s >resultat.txt",//
						comboBox.getModel().getElementAt(0),//
						ip.getText(),//
						subMask.getText());

		String cmdEnvoi2 = String
				.format(
						"cmd.exe /c netsh interface ip set address name=\"%s\" gateway=%s gwmetric=0 >resultat.txt",
						comboBox.getModel().getElementAt(0), passerelle
								.getText());
		String cmdEnvoi3 = String
				.format(
						"cmd.exe /c netsh interface ip set dns name=\"%s\" source=static addr=%s >resultat.txt",
						comboBox.getModel().getElementAt(0), dns1.getText());
		String cmdEnvoi4 = String
				.format(
						"cmd.exe /c netsh interface ip add dns name=\"%s\" addr=%s >resultat.txt",
						comboBox.getModel().getElementAt(0), dns2.getText());

		lstCmd.add(cmdEnvoi);
		lstCmd.add(cmdEnvoi2);
		lstCmd.add(cmdEnvoi3);
		lstCmd.add(cmdEnvoi4);

		try {
			Runtime r = Runtime.getRuntime();
			for (String cmd : lstCmd) {
				int idx = lstCmd.indexOf(cmd);
				switch (idx) {
					case 0:
						status
								.setText("configuration de l'adresse ip et du masque de sous reseau");
						// System.out.println("configuration de l'adresse ip et du masque de sous reseau");
						break;
					case 1:
						status.setText("configuration de la passerelle");
						// System.out.println("configuration de la passerelle");
						break;
					case 2:
						status.setText("configuration du dns1");
						// System.out.println("configuration du dns1");
						break;
					case 3:
						status.setText("configuration du dns2");
						// System.out.println("configuration du dns2");
						break;
				}
				Process p = r.exec(cmd);
				try {
					p.waitFor();
					verifiStatusApplication(idx);
				} catch (InterruptedException e1) {
					System.out.println("InterruptedException");
				}
			}

		} catch (IOException e1) {

			System.out.println("IOException");
		}

		if (ipApplique && passerelleApplique && dns1Applique && dns2Applique) {// tous
			// les
			// parametres
			// ont
			// été
			// appliqué
			// correctement

		} else {
			JOptionPane.showMessageDialog(null,
					"le profil demandé n'a pas été appliqué correctement",
					"Erreur", JOptionPane.WARNING_MESSAGE, null);

		}

		status.setText("");

		thread_lireIpConfig t = new thread_lireIpConfig(comboBox, ip, subMask,
				passerelle, dns1, dns2);
		t.start();

		fen.dispose();

	}

	private void verifiStatusApplication(int p_index) {
		ArrayList<String> lst = ReadFile.getContenu("./resultat.txt");
		boolean isOk = false;
		for (String ligne : lst) {
			if (ligne.contains("Ok.")) {
				isOk = true;

			}
		}
		switch (p_index) {
			case 0:
				if (isOk) {
					BtIp.setIcon(new ImageIcon(getClass().getResource(
							"/testbal/ok.png")));
					ipApplique = true;

				} else {
					BtIp.setIcon(new ImageIcon(getClass().getResource(
							"/testbal/erreur.png")));
				}
				BtIp.setVisible(true);
				break;
			case 1:
				if (isOk) {
					BtPasserelle.setIcon(new ImageIcon(getClass().getResource(
							"/testbal/ok.png")));
					passerelleApplique = true;
				} else {
					BtPasserelle.setIcon(new ImageIcon(getClass().getResource(
							"/testbal/erreur.png")));
				}
				BtPasserelle.setVisible(true);

				break;
			case 2:
				if (isOk) {
					BtDns1.setIcon(new ImageIcon(getClass().getResource(
							"/testbal/ok.png")));
					dns1Applique = true;
				} else {
					BtDns1.setIcon(new ImageIcon(getClass().getResource(
							"/testbal/erreur.png")));
				}
				BtDns1.setVisible(true);
				break;
			case 3:
				if (isOk) {
					BtDns2.setIcon(new ImageIcon(getClass().getResource(
							"/testbal/ok.png")));
					dns2Applique = true;
				} else {
					BtDns2.setIcon(new ImageIcon(getClass().getResource(
							"/testbal/erreur.png")));
				}
				BtDns2.setVisible(true);
				break;

		}

	}

}
