package thread;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import dialogue.ReadFile;

public class thread_lireIpConfig extends Thread {

	private JComboBox comboBox;
	private JTextField ip,subMask,passerelle,dns1,dns2;

	
	
	public thread_lireIpConfig (JComboBox p_comboBox,
			JTextField p_ip,JTextField p_subMask,JTextField p_passerelle,JTextField p_dns1,JTextField p_dns2){
		
		comboBox = p_comboBox;
		ip=p_ip;
		subMask=p_subMask;
		passerelle=p_passerelle;
		dns1=p_dns1;
		dns2=p_dns2;
		
	}
	
	public void run(){
		
		String cmdEnvoi = String
		.format(
				"cmd.exe /u /c  ipconfig/all >%s","ipconfig.txt");
			try {
				Runtime r = Runtime.getRuntime();
				Process p =  r.exec(cmdEnvoi);
				try {
					p.waitFor();
				} catch (InterruptedException e) {
					System.out.println("InterruptedException");
				}
			} catch (IOException e) {
			
				System.out.println("IOException");
			}
			
			ArrayList<String> lst = ReadFile.getContenu("./ipconfig.txt");
			//recherche dans la liste des infos qui nous interesse
			boolean ligneDNS2=false;
			DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
			for (String ligne:lst){
				if (ligne.contains("Connexion")){
					
					comboBoxModel.addElement(ligne.substring(ligne.lastIndexOf("Connexion"), ligne.lastIndexOf(":")));
					comboBox.setModel(comboBoxModel);
				}
				
				if (ligneDNS2==true){
					dns2.setText(ligne.substring(ligne.lastIndexOf(" ")+1));
					ligneDNS2=false;
				}
				if (ligne.contains("Adresse IP")){
					ip.setText(ligne.substring(ligne.lastIndexOf(":")+1));
				}
				if (ligne.contains("Masque de sous-réseau")){
					subMask.setText(ligne.substring(ligne.lastIndexOf(":")+1));
				}
				if(ligne.contains("Passerelle")){
					passerelle.setText(ligne.substring(ligne.lastIndexOf(":")+1));
				}
				if (ligne.contains("Serveurs DNS")){
					dns1.setText(ligne.substring(ligne.lastIndexOf(":")+1));
					ligneDNS2=true;
				}
				
			}

		
		
	}
	
}
