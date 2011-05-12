package dialogue;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import thread.thread_applique;
import thread.thread_lireIpConfig;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JList jList = null;
	private JButton jButtonAdd = null;
	private JButton jButtonRemove = null;
	private JButton jButtonApplique = null;
	private JTextField jLabelIP = null;
	private JTextField jLabelSubMask = null;
	private JTextField jLabelPasserelle = null;
	private JTextField jLabelDNS1 = null;
	private JTextField jLabelDNS2 = null;
	private JLabel ip = null;
	private JLabel subMask = null;
	private JLabel passerelle = null;
	private JLabel dns1 = null;
	private JLabel dns2 = null;
	private JComboBox jComboBox = null;
	private JLabel jLabelStatus = null;
	private DefaultListModel modelList;

	/**
	 * This method initializes jList
	 * @return javax.swing.JList
	 */
	private JList getJList() {
		if (jList == null) {
			jList = new JList(modelList);

			jList.setBounds(new Rectangle(25, 21, 196, 428));
			jList.addMouseListener(new java.awt.event.MouseAdapter() {

				public void mousePressed(java.awt.event.MouseEvent e) {
					int taille = modelList.getSize();
					if (taille != 0) {
						int idxSelected = jList.getSelectedIndex();
						String nomProfil = (String) modelList.get(idxSelected);
						// parcours le fichier profil.ipbs pour le supprimer
						ArrayList<String> list = ReadFile
								.getContenu("./profil.ipbs");

						for (String s : list) {
							if (s.contains(nomProfil)) {
								String[] tabChaine = s.split(";");
								jLabelIP.setText(tabChaine[1]);
								jLabelSubMask.setText(tabChaine[2]);
								jLabelPasserelle.setText(tabChaine[3]);
								jLabelDNS1.setText(tabChaine[4]);
								jLabelDNS2.setText(tabChaine[5]);
								return;
							}
						}

					}
				}
			});

		}
		return jList;
	}

	/**
	 * This method initializes jButtonAdd
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonAdd() {
		if (jButtonAdd == null) {
			jButtonAdd = new JButton();
			jButtonAdd.setBounds(new Rectangle(239, 26, 103, 34));
			jButtonAdd.setText("add");
			jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String nomProfil = JOptionPane
							.showInputDialog("Quel nom voulez vous donner a ce profil?");
					// creation de la chaine au format csv en vue du stockage
					StringBuilder sb = new StringBuilder();
					sb.append(nomProfil.trim() + " ;");
					sb.append(jLabelIP.getText().trim() + " ;");
					sb.append(jLabelSubMask.getText().trim() + " ;");
					sb.append(jLabelPasserelle.getText().trim() + " ;");
					sb.append(jLabelDNS1.getText().trim() + " ;");
					sb.append(jLabelDNS2.getText().trim() + " ;");

					String chaineASauver = sb.toString();
					WriteFile.WriteLine(chaineASauver, "./profil.ipbs");
					modelList.addElement(nomProfil);

				}
			});

		}
		return jButtonAdd;
	}

	/**
	 * This method initializes jButtonRemove
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonRemove() {
		if (jButtonRemove == null) {
			jButtonRemove = new JButton();
			jButtonRemove.setBounds(new Rectangle(237, 76, 103, 36));
			jButtonRemove.setText("remove");
			jButtonRemove
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							int taille = modelList.getSize();
							if (taille != 0) {
								int idxSelected = jList.getSelectedIndex();
								String nomProfil = (String) modelList
										.get(idxSelected);
								// parcours le fichier profil.ipbs pour le
								// supprimer
								ArrayList<String> list = ReadFile
										.getContenu("./profil.ipbs");
								ArrayList<String> newLst = new ArrayList<String>();

								for (String s : list) {
									if (s.contains(nomProfil)) {

									} else {
										newLst.add(s);
									}
								}
								WriteFile.WriteList(newLst, "./profil.ipbs");
								modelList.remove(idxSelected);
							}
						}
					});
		}
		return jButtonRemove;
	}

	/**
	 * This method initializes jButtonApplique
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonApplique() {
		if (jButtonApplique == null) {
			jButtonApplique = new JButton();
			jButtonApplique.setBounds(new Rectangle(237, 134, 103, 40));
			jButtonApplique.setText("Applique");
			jButtonApplique
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							thread_applique t = new thread_applique(
									jLabelStatus, jComboBox, jLabelIP,
									jLabelSubMask, jLabelPasserelle,
									jLabelDNS1, jLabelDNS2);
							t.start();
							// while (t.isAlive()){
							// //do nothing
							// //System.out.println("t.isAlive");
							// }
							// getInfoFromIpConfig();

						}
					});
		}
		return jButtonApplique;
	}

	/**
	 * This method initializes jComboBox
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBox() {
		if (jComboBox == null) {
			jComboBox = new JComboBox();
			jComboBox.setBounds(new Rectangle(245, 208, 454, 40));
		}
		return jComboBox;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Main thisClass = new Main();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public Main() {
		super();
		modelList = new DefaultListModel();
		initialize();

		getInfoFromIpConfig();
		ArrayList<String> lst = ReadFile.getContenu("./profil.ipbs");
		for (String s : lst) {
			String[] tabChaine = s.split(";");
			modelList.addElement(tabChaine[0]);
		}

	}

	private void getInfoFromIpConfig() {
		thread_lireIpConfig t = new thread_lireIpConfig(jComboBox, jLabelIP,
				jLabelSubMask, jLabelPasserelle, jLabelDNS1, jLabelDNS2);
		t.start();
	}

	// /**
	// * Retourne toutes les adresses ips des carte réseau de la machine.
	// Retourne seulement les addresses IPV4
	// *
	// * @return Une liste des addresses ip
	// */
	// public List<String> getIps(){
	// List<String> ips = new ArrayList<String>();
	//			
	// try{
	// Enumeration<NetworkInterface> interfaces =
	// NetworkInterface.getNetworkInterfaces();
	//			                
	// while (interfaces.hasMoreElements()) { // carte reseau trouvee
	// NetworkInterface interfaceN = (NetworkInterface)interfaces.nextElement();
	// Enumeration<InetAddress> ienum = interfaceN.getInetAddresses();
	// while (ienum.hasMoreElements()) { // retourne l adresse IPv4 et IPv6
	// InetAddress ia = ienum.nextElement();
	// String adress = ia.getHostAddress().toString();
	//		                
	// if( adress.length() < 16){ //On s'assure ainsi que l'adresse IP est bien
	// IPv4
	// if(adress.startsWith("127")){ //Ce n'est pas l'adresse IP Local'
	// // System.out.println(ia.getHostAddress());
	// }
	// else if(adress.indexOf(":") > 0){
	// // System.out.println(ia.getHostAddress()); // les ":" indique que c'est
	// une IPv6"
	// }
	// }
	//		                
	// ips.add(adress);
	// }
	// }
	// }
	// catch(Exception e){
	// System.out.println("pas de carte reseau");
	// e.printStackTrace();
	// }
	//		    
	// return ips;
	// }
	//
	//		
	//	
	//
	// private void getInfoIPActuelle() {
	// InetAddress Ip = null;
	// try {
	// Ip=InetAddress.getLocalHost();
	// System.out.println("getLocalHost: "+Ip);
	// System.out.println("getHostAdress: "+Ip.getHostAddress());
	// System.out.println("getCanonicalHostName: "+Ip.getCanonicalHostName());
	// System.out.println("getHostName: "+Ip.getHostName());
	// InetAddress[] tab = InetAddress.getAllByName(Ip.getHostName());
	// for (InetAddress ligne:tab){
	// System.out.println("adresse: "+ligne);
	// }
	// jLabelIP.setText(Ip.getHostAddress());
	//			 
	//			 
	//			 
	// } catch (UnknownHostException e) {
	// System.out.println("Hote inconnu");
	// }
	//		
	//		
	//		
	// }
	//	
	// private void getInfoIPV4(){
	// InetAddress ipLocalHost=null;
	// try {
	// ipLocalHost = InetAddress.getLocalHost();
	// } catch (UnknownHostException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// System.out.println("Ip localHost: "+ipLocalHost);
	// InetAddress ipV4=null;
	// try {
	// ipV4 = Inet4Address.getByAddress(ipLocalHost.getAddress());
	// } catch (UnknownHostException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// System.out.println("IPV4 :"+ipV4);
	// }
	// private void getInfoIPV6(){
	// InetAddress ipLocalHost = null;
	// try {
	// ipLocalHost = InetAddress.getLocalHost();
	// } catch (UnknownHostException e) {
	// System.out.println("UnknownHostException");
	// }
	// System.out.println("Ip localHost: "+ipLocalHost);
	// NetworkInterface nif = null;
	// try {
	// nif = NetworkInterface.getByInetAddress(ipLocalHost);
	// } catch (SocketException e) {
	// System.out.println("SocketException");
	// }
	//	
	//		
	// System.out.println("network interface getName: "+nif.getName());
	// System.out.println("network interface getDisplayName: "+nif.getDisplayName());
	//		
	// Inet6Address IpV6 = null;
	// try {
	// IpV6 = Inet6Address.getByAddress(ipLocalHost.getHostName(),
	// ipLocalHost.getAddress(), nif);
	// } catch (UnknownHostException e) {
	// System.out.println("UnknownHostException");
	// }
	// System.out.println(IpV6);
	// }

	/**
	 * This method initializes this
	 * @return void
	 */
	private void initialize() {
		this.setSize(748, 502);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/LogoPrincipal.png")));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(getJContentPane());
		this.setTitle("IP config By Sims");
		this.addWindowListener(new java.awt.event.WindowAdapter() {

			public void windowClosing(final java.awt.event.WindowEvent e) {
				exit();
			}
		});
		this.setLocationRelativeTo(null); // On centre la fenêtre sur l'écran
	}

	private void exit() {
		System.exit(0);

	}

	/**
	 * This method initializes jContentPane
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabelStatus = new JLabel();
			jLabelStatus.setBounds(new Rectangle(352, 72, 347, 44));
			jLabelStatus.setText("");

			dns2 = new JLabel();
			dns2.setBounds(new Rectangle(649, 374, 50, 30));
			dns2.setText("DNS2");
			dns1 = new JLabel();
			dns1.setBounds(new Rectangle(245, 381, 46, 28));
			dns1.setText("DSN1");
			passerelle = new JLabel();
			passerelle.setBounds(new Rectangle(441, 320, 63, 21));
			passerelle.setText("Passerelle");
			subMask = new JLabel();
			subMask.setBounds(new Rectangle(551, 262, 148, 17));
			subMask.setText("Masque de sous reseau :");
			ip = new JLabel();
			ip.setBounds(new Rectangle(245, 257, 78, 20));
			ip.setText("Adresse IP:");
			jLabelDNS2 = new JTextField();
			jLabelDNS2.setBounds(new Rectangle(518, 407, 181, 44));
			jLabelDNS2.setText("");
			jLabelDNS1 = new JTextField();
			jLabelDNS1.setBounds(new Rectangle(245, 410, 176, 40));
			jLabelDNS1.setText("");
			jLabelPasserelle = new JTextField();
			jLabelPasserelle.setBounds(new Rectangle(386, 343, 173, 33));
			jLabelPasserelle.setText("");
			jLabelSubMask = new JTextField();
			jLabelSubMask.setBounds(new Rectangle(533, 283, 166, 36));
			jLabelSubMask.setText("");
			jLabelIP = new JTextField();
			jLabelIP.setBounds(new Rectangle(245, 281, 169, 38));
			jLabelIP.setText("");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJList(), null);
			jContentPane.add(getJButtonAdd(), null);
			jContentPane.add(getJButtonRemove(), null);
			jContentPane.add(getJButtonApplique(), null);
			jContentPane.add(jLabelIP, null);
			jContentPane.add(jLabelSubMask, null);
			jContentPane.add(jLabelPasserelle, null);
			jContentPane.add(jLabelDNS1, null);
			jContentPane.add(jLabelDNS2, null);
			jContentPane.add(ip, null);
			jContentPane.add(subMask, null);
			jContentPane.add(passerelle, null);
			jContentPane.add(dns1, null);
			jContentPane.add(dns2, null);
			jContentPane.add(getJComboBox(), null);
			jContentPane.add(jLabelStatus, null);
		}
		return jContentPane;
	}

	// /**
	// * Gets all matching dns records as an array of strings.
	// *
	// * @param domain domain, e.g. oberon.ark.com or oberon.com which you want
	// * the DNS records.
	// * @param types e.g."MX","A" to describe which types of record you want.
	// *
	// * @return ArrayList of Strings
	// *
	// * @throws NamingException if DNS lookup fails.
	// */
	//   
	// private static ArrayList<String> getDNSRecs( String domain,
	// String... types ) throws NamingException
	// {
	// ArrayList<String> results = new ArrayList<String>( 15 );
	//
	// // Old Java 1.3 style required you to provide an explicit DNS server.
	// // DirContext ictx = new InitialDirContext();
	// // Attributes attrs =
	// // ictx.getAttributes( "dns://" + DNS_SERVER + "/" + domain,
	// // types );
	//
	// Hashtable<String,String> env = new Hashtable<String,String>();
	// env.put( "java.naming.factory.initial",
	// "com.sun.jndi.dns.DnsContextFactory" );
	// DirContext ictx = new InitialDirContext( env );
	// Attributes attrs = ictx.getAttributes( domain, types );
	// for ( Enumeration e = attrs.getAll(); e.hasMoreElements(); )
	// {
	// Attribute a = (Attribute) e.nextElement();
	// int size = a.size();
	// for ( int i = 0; i < size; i++ )
	// {
	// // MX string has priority (lower better) followed by associated
	// mailserver
	// // A string is just IP
	// results.add( (String) a.get( i ) );
	// }// end inner for
	// }// end outer for
	// if ( DEBUGGING && results.size() == 0 )
	// {
	// System.err
	// .println( "Failed to find any DNS records for domain "
	// + domain );
	// }
	// return results;
	// }

} // @jve:decl-index=0:visual-constraint="10,10"
