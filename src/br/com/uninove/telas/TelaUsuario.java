/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.uninove.telas;

import java.sql.*;
import br.com.uninove.dal.ModuloConexao;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JOptionPane;

public class TelaUsuario extends javax.swing.JInternalFrame {

    // Variavel que Cirei no Módulo de Conexao
    Connection conexao = null;
    // Bibliotéca para Manipulçar Código SQL
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaUsuario
     */
    public TelaUsuario() {
	initComponents();
	getContentPane().setBackground(new Color(236, 236, 236));
	conexao = ModuloConexao.conector();
    }

    //Método para consultar usuários
    private void consultar() {
	String sql = "select * from tbusuarios where iduser=?";
	try {
	    pst = conexao.prepareStatement(sql);
	    pst.setString(1, txtUsuId.getText());
	    rs = pst.executeQuery();

	    if (rs.next()) {
		txtUsuNome.setText(rs.getString(2));
		txtUsuFone.setText(rs.getString(3));
		txtUsuLogin.setText(rs.getString(4));
		txtUsuSenha.setText(rs.getString(5));
		cboUsuPerfil.setSelectedItem(rs.getString(6));
	    } else {
		JOptionPane.showMessageDialog(null, "Usuário não cadastrado");
		//Limpa os campos
		txtUsuNome.setText(null);
		txtUsuFone.setText(null);
		txtUsuLogin.setText(null);
		txtUsuSenha.setText(null);
	    }
	} catch (SQLException e) {
	    JOptionPane.showMessageDialog(null, e);
	}
    }

    //Método para adicionar usuários
    private void adicionar() {
	String sql = "insert into tbusuarios(iduser,usuario,fone,login,senha,perfil) values(?,?,?,?,?,?)";

	try {
	    pst = conexao.prepareStatement(sql);
	    pst.setString(1, txtUsuId.getText());
	    pst.setString(2, txtUsuNome.getText());
	    pst.setString(3, txtUsuFone.getText());
	    pst.setString(4, txtUsuLogin.getText());
	    pst.setString(5, txtUsuSenha.getText());
	    pst.setString(6, cboUsuPerfil.getSelectedItem().toString());
	    //Validação dos campos obrigatórios
	    if ((txtUsuId.getText().isEmpty()) || (txtUsuNome.getText().isEmpty()) || (txtUsuLogin.getText().isEmpty()) || (txtUsuSenha.getText().isEmpty())) {
		JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
	    } else {
		//Atualiza a tabela usuario com os dados do formulário

		//Confirma a inserção dos dados na tabela
		int adiconado = pst.executeUpdate();
		//se funcionar, retornará 1, que significa a adição de uma linha ao banco
		if (adiconado > 0) {
		    JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso!");
		    //Limpa os campos
		    txtUsuId.setText(null);
		    txtUsuNome.setText(null);
		    txtUsuFone.setText(null);
		    txtUsuLogin.setText(null);
		    txtUsuSenha.setText(null);
		}
	    }
	} catch (Exception e) {
	    JOptionPane.showMessageDialog(null, e);
	}
    }

    //Método para alterar usuários
    private void alterar() {
	String sql = "update tbusuarios set usuario = ?, fone = ?, login = ?, senha = ?, perfil = ? where iduser = ?";

	try {
	    pst = conexao.prepareStatement(sql);
	    pst.setString(1, txtUsuNome.getText());
	    pst.setString(2, txtUsuFone.getText());
	    pst.setString(3, txtUsuLogin.getText());
	    pst.setString(4, txtUsuSenha.getText());
	    pst.setString(5, cboUsuPerfil.getSelectedItem().toString());
	    pst.setString(6, txtUsuId.getText());
	    //Validação dos campos obrigatórios
	    if ((txtUsuId.getText().isEmpty()) || (txtUsuNome.getText().isEmpty()) || (txtUsuLogin.getText().isEmpty()) || (txtUsuSenha.getText().isEmpty())) {
		JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
	    } else {
		//Atualiza a tabela usuario com os dados do formulário

		//Confirma a inserção dos dados na tabela
		int adiconado = pst.executeUpdate();
		//se funcionar, retornará 1, que significa a adição de uma linha ao banco
		if (adiconado > 0) {
		    JOptionPane.showMessageDialog(null, "Dados do usuário alterado com sucesso!");
		    //Limpa os campos
		    txtUsuId.setText(null);
		    txtUsuNome.setText(null);
		    txtUsuFone.setText(null);
		    txtUsuLogin.setText(null);
		    txtUsuSenha.setText(null);
		}
	    }
	} catch (Exception e) {
	    JOptionPane.showMessageDialog(null, e);
	}
    }

    //Método para remover usuários
    private void remover() {
	int confirmar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usuário?", "Atenção", JOptionPane.YES_NO_OPTION);
	if (confirmar == JOptionPane.YES_OPTION) {
	    String sql = "delete from tbusuarios where iduser=?";
	    try {
		pst = conexao.prepareStatement(sql);
		pst.setString(1, txtUsuId.getText());
		int apagado = pst.executeUpdate();
		if (apagado > 0) {
		    JOptionPane.showMessageDialog(null, "Usuário removido com sucesso!");
		    //Limpa os campos
		    txtUsuId.setText(null);
		    txtUsuNome.setText(null);
		    txtUsuFone.setText(null);
		    txtUsuLogin.setText(null);
		    txtUsuSenha.setText(null);
		}
	    } catch (Exception e) {
		JOptionPane.showMessageDialog(null, e);
	    }
	}
    }

    @Override
    public void print(Graphics g) {
	super.print(g); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cboUsuPerfil = new javax.swing.JComboBox<>();
        txtUsuId = new javax.swing.JTextField();
        txtUsuNome = new javax.swing.JTextField();
        txtUsuFone = new javax.swing.JTextField();
        txtUsuLogin = new javax.swing.JTextField();
        txtUsuSenha = new javax.swing.JTextField();
        btnUsuCreate = new javax.swing.JButton();
        btnUsoRead = new javax.swing.JButton();
        btnUsuUpdate = new javax.swing.JButton();
        btnUsoDelete = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Usuários");
        setPreferredSize(new java.awt.Dimension(528, 487));

        jLabel1.setText("*id");
        jLabel1.setPreferredSize(new java.awt.Dimension(40, 16));

        jLabel2.setText("*Nome");
        jLabel2.setPreferredSize(new java.awt.Dimension(40, 16));

        jLabel3.setText("Fone");
        jLabel3.setPreferredSize(new java.awt.Dimension(40, 16));
        jLabel3.setSize(new java.awt.Dimension(0, 16));

        jLabel4.setText("*Senha");
        jLabel4.setPreferredSize(new java.awt.Dimension(40, 16));

        jLabel5.setText("*Login");
        jLabel5.setPreferredSize(new java.awt.Dimension(40, 16));

        jLabel6.setText("*Perfil");
        jLabel6.setPreferredSize(new java.awt.Dimension(40, 16));

        cboUsuPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "user" }));

        txtUsuId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuIdActionPerformed(evt);
            }
        });

        txtUsuSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuSenhaActionPerformed(evt);
            }
        });

        btnUsuCreate.setText("Adicionar");
        btnUsuCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuCreateActionPerformed(evt);
            }
        });

        btnUsoRead.setText("Consultar");
        btnUsoRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsoReadActionPerformed(evt);
            }
        });

        btnUsuUpdate.setText("Alterar");
        btnUsuUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuUpdateActionPerformed(evt);
            }
        });

        btnUsoDelete.setText("Remover");
        btnUsoDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsoDeleteActionPerformed(evt);
            }
        });

        jLabel7.setText("* Campos obrigatórios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnUsuCreate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUsoRead)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUsuUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUsoDelete)
                        .addContainerGap(32, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtUsuId, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtUsuFone)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(txtUsuLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                        .addGap(6, 6, 6)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cboUsuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtUsuSenha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(txtUsuNome))))
                        .addGap(34, 34, 34))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnUsoDelete, btnUsoRead, btnUsuCreate, btnUsuUpdate});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsuId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboUsuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsuLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsuSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUsuCreate)
                    .addComponent(btnUsoRead)
                    .addComponent(btnUsuUpdate)
                    .addComponent(btnUsoDelete))
                .addContainerGap(175, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuIdActionPerformed
	// TODO add your handling code here:
    }//GEN-LAST:event_txtUsuIdActionPerformed

    private void txtUsuSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuSenhaActionPerformed
	// TODO add your handling code here:
    }//GEN-LAST:event_txtUsuSenhaActionPerformed

    private void btnUsoReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsoReadActionPerformed
	// Chamando o método consultar
	consultar();
    }//GEN-LAST:event_btnUsoReadActionPerformed

    private void btnUsuCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuCreateActionPerformed
	// Chamando o método adicionar
	adicionar();
    }//GEN-LAST:event_btnUsuCreateActionPerformed

    private void btnUsuUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuUpdateActionPerformed
	// Chamando o método alterar
	alterar();
    }//GEN-LAST:event_btnUsuUpdateActionPerformed

    private void btnUsoDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsoDeleteActionPerformed
	// Chamando o método remover
	remover();
    }//GEN-LAST:event_btnUsoDeleteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUsoDelete;
    private javax.swing.JButton btnUsoRead;
    private javax.swing.JButton btnUsuCreate;
    private javax.swing.JButton btnUsuUpdate;
    private javax.swing.JComboBox<String> cboUsuPerfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtUsuFone;
    private javax.swing.JTextField txtUsuId;
    private javax.swing.JTextField txtUsuLogin;
    private javax.swing.JTextField txtUsuNome;
    private javax.swing.JTextField txtUsuSenha;
    // End of variables declaration//GEN-END:variables
}
