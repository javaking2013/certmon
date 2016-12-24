package com.jk.certmon.display;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;

import javax.swing.*;

import com.jk.certmon.utility.Constants;
import com.jk.certmon.utility.GetCert;

public class certmon {
	
	public static void main(String[] args) {
		new certmon();
	}
	
	public static JFrame f;
	public static JScrollPane certsListPane, certDetailsPane;
	public static JList<String> list;
	public static JTextArea certDetailsArea;
	public static JMenuBar menuBar;
	public static JMenu file, tools, help;
	public static JTextField fileField, pwField;
	public static DefaultListModel<String> listModel;
	
	public certmon(){
		showit();
		btnAction();
	}
	
	private static void showit(){
		f = new JFrame("CertMon " + Constants.VERSION);
		JPanel upperPanel = new JPanel();
		
		menuBar = new JMenuBar();
		
		file = new JMenu("File");
		tools = new JMenu("Tools");
		help = new JMenu("Help");
		
		menuBar.add(file);
		menuBar.add(tools);
		menuBar.add(help);

		tools.add(Items.getMenuItem("Get Cert Value"));
		tools.add(Items.getMenuItem("Get Remote Certificate"));

		file.add(Items.getMenuItem("Choose File"));
		file.add(Items.getMenuItem("Exit"));
		
		//tools.add(Items.getMenuItem("List Cert"));
		
		help.add(Items.getMenuItem("About"));
		
		fileField = new JTextField(40);
		fileField.setText(System.getProperty("java.home") + "/lib/security/cacerts".replace('/', File.separatorChar));
		pwField = new JTextField(10);
		pwField.setText(Constants.CERT_PASSWORD);
		
		listModel = new DefaultListModel<>();
		GetCert.getCertList();
		list = new JList<>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		certsListPane = new JScrollPane(list);
		certsListPane.setPreferredSize(new Dimension(250,80));

		certDetailsArea = new JTextArea(20,10);
		certDetailsArea.setEditable(false);
		certDetailsArea.setWrapStyleWord(true);
		certDetailsArea.setLineWrap(true);
		certDetailsPane = new JScrollPane(certDetailsArea);
		certDetailsPane.setPreferredSize(new Dimension(250,80));
		certDetailsPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		upperPanel.add(new JLabel("Filename: "));
		upperPanel.add(fileField);
		upperPanel.add(Items.getButton("Choose File"));
		upperPanel.add(new JLabel("Password:"));
		upperPanel.add(pwField);
		
		Font font = new Font("Courier New", Font.PLAIN, 12);
		certDetailsArea.setFont(font);
		
		f.add(certsListPane,BorderLayout.LINE_START);
		f.add(certDetailsPane,BorderLayout.CENTER);
		f.add(upperPanel,BorderLayout.PAGE_START);
		f.setJMenuBar(menuBar);
		f.setTitle(Constants.TITLE);
		f.setResizable(true);
		f.setSize(900,500);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	private static void btnAction(){
		list.addListSelectionListener((ListSelectionEvent) -> {
			certmon.certDetailsArea.setText(GetCert.getCertString(certmon.list.getSelectedValue()));
			certmon.certDetailsArea.setCaretPosition(0);
			Constants.CURRENT_CERT = list.getSelectedValue();
		});
	}
}
