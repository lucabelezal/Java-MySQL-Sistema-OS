/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.uninove.telas;

import br.com.uninove.dal.ModuloConexao;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
//Importacao da biblioteca rs2xml.jar
import net.proteanit.sql.DbUtils;
import sun.awt.image.PixelConverter;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class TelaCliente extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaCliente
     */
    public TelaCliente() {
	initComponents();
	getContentPane().setBackground(new Color(236, 236, 236));
	conexao = ModuloConexao.conector();
    }

    //Método para adicionar usuários
    private void adicionar() {
	String sql = "insert into tbclientes(nomecli, endcli, fonecli, emailcli) values(?,?,?,?)";

	try {
	    pst = conexao.prepareStatement(sql);
	    pst.setString(1, txtCliNome.getText());
	    pst.setString(2, txtCliEndereco.getText());
	    pst.setString(3, txtCliFone.getText());
	    pst.setString(4, txtCliEmail.getText());
	    //Validação dos campos obrigatórios
	    if ((txtCliNome.getText().isEmpty()) || (txtCliFone.getText().isEmpty())) {
		JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
	    } else {
		//Atualiza a tabela usuario com os dados do formulário

		//Confirma a inserção dos dados na tabela
		int adiconado = pst.executeUpdate();
		//se funcionar, retornará 1, que significa a adição de uma linha ao banco
		if (adiconado > 0) {
		    JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso!");
		    //Limpa os campos
		    txtCliNome.setText(null);
		    txtCliEndereco.setText(null);
		    txtCliFone.setText(null);
		    txtCliEmail.setText(null);
		}
	    }
	} catch (Exception e) {
	    JOptionPane.showMessageDialog(null, e);
	}
    }

    //Método para pesquisar clientes pelo nome com filtro
    private void pesquisar_clientes() {

	String sql = "select * from tbclientes where nomecli like ?";
	try {
	    pst = conexao.prepareStatement(sql);
	    //Faz a pesquisa no banco de acordo com as letras escritas na campo pesquisa
	    pst.setString(1, txtCliPesquisar.getText() + "%");
	    rs = pst.executeQuery();

	    //Preenche o campo na tabela
	    tblClientes.setModel(DbUtils.resultSetToTableModel(rs));
	} catch (Exception e) {
	    JOptionPane.showMessageDialog(null, e);
	}

    }

    //Método para trazer os registros do banco para os campos da TelaCliente
    public void setar_campos() {
	int setar = tblClientes.getSelectedRow();
	txtCliId.setText(tblClientes.getModel().getValueAt(setar, 0).toString());
	txtCliNome.setText(tblClientes.getModel().getValueAt(setar, 1).toString());
	txtCliEndereco.setText(tblClientes.getModel().getValueAt(setar, 2).toString());
	txtCliFone.setText(tblClientes.getModel().getValueAt(setar, 3).toString());
	txtCliEmail.setText(tblClientes.getModel().getValueAt(setar, 4).toString());

	//Desabilitar botao adicionar
	btnAdicionar.setEnabled(false);
    }

    //Método para alterar dados do cliente
    //Método para alterar usuários
    private void alterar() {
	String sql = "update tbclientes set nomecli = ?, endcli = ?, fonecli = ?, emailcli = ? where idcli = ?";

	try {
	    pst = conexao.prepareStatement(sql);
	    pst.setString(1, txtCliNome.getText());
	    pst.setString(2, txtCliEndereco.getText());
	    pst.setString(3, txtCliFone.getText());
	    pst.setString(4, txtCliEmail.getText());
	    pst.setString(5, txtCliId.getText());

	    //Validação dos campos obrigatórios
	    if ((txtCliNome.getText().isEmpty()) || (txtCliFone.getText().isEmpty())) {
		JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
	    } else {
		//Atualiza a tabela usuario com os dados do formulário

		//Confirma a inserção dos dados na tabela
		int adiconado = pst.executeUpdate();
		//se funcionar, retornará 1, que significa a adição de uma linha ao banco
		if (adiconado > 0) {
		    JOptionPane.showMessageDialog(null, "Dados do usuário alterado com sucesso!");
		    //Limpa os campos
		    txtCliNome.setText(null);
		    txtCliEndereco.setText(null);
		    txtCliFone.setText(null);
		    txtCliEmail.setText(null);
		    //Habilitar botao adicionar
		    btnAdicionar.setEnabled(true);
		}
	    }
	} catch (Exception e) {
	    JOptionPane.showMessageDialog(null, e);
	}
    }

    //Método para remover usuários
    private void remover() {
	int confirmar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este cliente?", "Atenção", JOptionPane.YES_NO_OPTION);
	if (confirmar == JOptionPane.YES_OPTION) {
	    String sql = "delete from tbclientes where idcli=?";
	    try {
		pst = conexao.prepareStatement(sql);
		pst.setString(1, txtCliId.getText());
		int apagado = pst.executeUpdate();
		if (apagado > 0) {
		    JOptionPane.showMessageDialog(null, "Cliente removido com sucesso!");
		    //Limpa os campos
		    txtCliNome.setText(null);
		    txtCliEndereco.setText(null);
		    txtCliFone.setText(null);
		    txtCliEmail.setText(null);
		    //Habilitar botao adicionar
		    btnAdicionar.setEnabled(true);
		}
	    } catch (Exception e) {
		JOptionPane.showMessageDialog(null, e);
	    }
	}
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
        txtCliFone = new javax.swing.JTextField();
        txtCliEmail = new javax.swing.JTextField();
        txtCliNome = new javax.swing.JTextField();
        txtCliEndereco = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCliPesquisar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtCliId = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Clientes");
        setPreferredSize(new java.awt.Dimension(528, 487));

        jLabel1.setText("*Nome");

        jLabel2.setText("Endereço");

        jLabel3.setText("*Telefone");

        jLabel4.setText("Email");

        txtCliFone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliFoneActionPerformed(evt);
            }
        });

        btnAdicionar.setText("Adicionar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnRemover.setText("Remover");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        jLabel5.setText("*Campo obrigatórios");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/uninove/icones/search03.png"))); // NOI18N
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        txtCliPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliPesquisarKeyReleased(evt);
            }
        });

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        jLabel7.setText("Id Cliente:");

        txtCliId.setEnabled(false);
        txtCliId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliIdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCliNome)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtCliFone)
                                        .addComponent(txtCliEndereco)
                                        .addComponent(txtCliEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 25, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(16, 16, 16))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCliEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCliFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtCliEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnAdicionar)
                    .addComponent(btnAlterar)
                    .addComponent(btnRemover))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCliFoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliFoneActionPerformed
	// TODO add your handling code here:
    }//GEN-LAST:event_txtCliFoneActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
	// Chamando o método remover cliente
	remover();
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
	// Chamando o método para adicionar clientes
	adicionar();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    //Evento aplicado enquanto usuário for digitando"
    private void txtCliPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliPesquisarKeyReleased
	//Chamando o método pesquisar clientes
	pesquisar_clientes();

    }//GEN-LAST:event_txtCliPesquisarKeyReleased

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked

    }//GEN-LAST:event_jScrollPane1MouseClicked

    //Evento aplicado quando clicar no registro da tabela e enviar para os respectivos campos
    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
	// Chamando o método que envia os dados para os campos da tela
	setar_campos();
    }//GEN-LAST:event_tblClientesMouseClicked

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
	// Chamando o método para alterar clientes
	alterar();
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void txtCliIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliIdActionPerformed
	// TODO add your handling code here:
    }//GEN-LAST:event_txtCliIdActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtCliEmail;
    private javax.swing.JTextField txtCliEndereco;
    private javax.swing.JTextField txtCliFone;
    private javax.swing.JTextField txtCliId;
    private javax.swing.JTextField txtCliNome;
    private javax.swing.JTextField txtCliPesquisar;
    // End of variables declaration//GEN-END:variables
}
