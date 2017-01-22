/*
 * Autopsy Forensic Browser
 *
 * Copyright 2011-2016 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

 /*
 * CasePropertiesForm.java
 *
 * Created on Mar 14, 2011, 1:48:20 PM
 */
package org.sleuthkit.autopsy.casemodule;

import java.io.File;
import java.util.Map;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.NbBundle;
import org.openide.util.actions.CallableSystemAction;
import org.sleuthkit.autopsy.coreutils.Logger;

/**
 * The form where user can change / update the properties of the current case
 * metadata.
 */
class CasePropertiesForm extends javax.swing.JPanel {
    
    private static final long serialVersionUID = 1L;
    
    private Case current = null;
    private static JPanel caller;    // panel for error

    // Shrink a path to fit in targetLength (if necessary), by replaceing part
    // of the path with "...". Ex: "C:\Users\bob\...\folder\other\Image.img"
    private String shrinkPath(String path, int targetLength) {
        if (path.length() > targetLength) {
            String fill = "...";
            
            int partsLength = targetLength - fill.length();
            
            String front = path.substring(0, partsLength / 4);
            int frontSep = front.lastIndexOf(File.separatorChar);
            if (frontSep != -1) {
                front = front.substring(0, frontSep + 1);
            }
            
            String back = path.substring(partsLength * 3 / 4);
            int backSep = back.indexOf(File.separatorChar);
            if (backSep != -1) {
                back = back.substring(backSep);
            }
            return back + fill + front;
        } else {
            return path;
        }
    }

    /**
     * Creates new form CasePropertiesForm
     */
    CasePropertiesForm(Case currentCase, String crDate, String caseDir, Map<Long, String> imgPaths) throws CaseMetadata.CaseMetadataException {
        initComponents();
        caseNameTextField.setText(currentCase.getName());
        String caseNumber = currentCase.getNumber();
        if (!caseNumber.equals("")) {
            caseNumberField.setText(caseNumber);
        } else {
            caseNumberField.setText("N/A");
        }
        String examiner = currentCase.getExaminer();
        if (!examiner.equals("")) {
            examinerField.setText(examiner);
        } else {
            examinerField.setText("N/A");
        }
        crDateField.setText(crDate);
        caseDirField.setText(caseDir);
        current = currentCase;
        
        CaseMetadata caseMetadata = currentCase.getCaseMetadata();
        if (caseMetadata.getCaseType() == Case.CaseType.SINGLE_USER_CASE) {
            dbNameField.setText(caseMetadata.getCaseDatabasePath());
        } else {
            dbNameField.setText(caseMetadata.getCaseDatabaseName());
        }
        Case.CaseType caseType = caseMetadata.getCaseType();
        caseTypeField.setText(caseType.getLocalizedDisplayName());
        if (caseType == Case.CaseType.SINGLE_USER_CASE) {
            deleteCaseButton.setEnabled(true);
        } else {
            deleteCaseButton.setEnabled(false);
        }
    }

    /**
     * In this generated code below, there are 2 strings "Path" and "Remove"
     * that are table column headers in the DefaultTableModel. When this model
     * is generated, it puts the hard coded English strings into the generated
     * code. And then about 15 lines later, it separately internationalizes them
     * using: imagesTable.getColumnModel().getColumn(0).setHeaderValue(). There
     * is no way to prevent the GUI designer from putting the hard coded English
     * strings into the generated code. So, they remain, and are not used.
     */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        caseNameLabel = new javax.swing.JLabel();
        crDateLabel = new javax.swing.JLabel();
        caseDirLabel = new javax.swing.JLabel();
        caseNameTextField = new javax.swing.JTextField();
        updateCaseNameButton = new javax.swing.JButton();
        deleteCaseButton = new javax.swing.JButton();
        caseNumberLabel = new javax.swing.JLabel();
        examinerLabel = new javax.swing.JLabel();
        lbDbType = new javax.swing.JLabel();
        lbDbName = new javax.swing.JLabel();
        caseNumberField = new javax.swing.JLabel();
        examinerField = new javax.swing.JLabel();
        crDateField = new javax.swing.JLabel();
        caseDirField = new javax.swing.JLabel();
        dbNameField = new javax.swing.JLabel();
        caseTypeField = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        caseNameLabel.setFont(caseNameLabel.getFont().deriveFont(caseNameLabel.getFont().getStyle() & ~java.awt.Font.BOLD, 11));
        caseNameLabel.setText(org.openide.util.NbBundle.getMessage(CasePropertiesForm.class, "CasePropertiesForm.caseNameLabel.text")); // NOI18N

        crDateLabel.setFont(crDateLabel.getFont().deriveFont(crDateLabel.getFont().getStyle() & ~java.awt.Font.BOLD, 11));
        crDateLabel.setText(org.openide.util.NbBundle.getMessage(CasePropertiesForm.class, "CasePropertiesForm.crDateLabel.text")); // NOI18N

        caseDirLabel.setFont(caseDirLabel.getFont().deriveFont(caseDirLabel.getFont().getStyle() & ~java.awt.Font.BOLD, 11));
        caseDirLabel.setText(org.openide.util.NbBundle.getMessage(CasePropertiesForm.class, "CasePropertiesForm.caseDirLabel.text")); // NOI18N

        caseNameTextField.setFont(caseNameTextField.getFont().deriveFont(caseNameTextField.getFont().getStyle() & ~java.awt.Font.BOLD, 11));
        caseNameTextField.setText(org.openide.util.NbBundle.getMessage(CasePropertiesForm.class, "CasePropertiesForm.caseNameTextField.text")); // NOI18N
        caseNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caseNameTextFieldActionPerformed(evt);
            }
        });

        updateCaseNameButton.setFont(updateCaseNameButton.getFont().deriveFont(updateCaseNameButton.getFont().getStyle() & ~java.awt.Font.BOLD, 11));
        updateCaseNameButton.setText(org.openide.util.NbBundle.getMessage(CasePropertiesForm.class, "CasePropertiesForm.updateCaseNameButton.text")); // NOI18N
        updateCaseNameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateCaseNameButtonActionPerformed(evt);
            }
        });

        deleteCaseButton.setFont(deleteCaseButton.getFont().deriveFont(deleteCaseButton.getFont().getStyle() & ~java.awt.Font.BOLD, 11));
        deleteCaseButton.setText(org.openide.util.NbBundle.getMessage(CasePropertiesForm.class, "CasePropertiesForm.deleteCaseButton.text")); // NOI18N
        deleteCaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteCaseButtonActionPerformed(evt);
            }
        });

        caseNumberLabel.setFont(caseNumberLabel.getFont().deriveFont(caseNumberLabel.getFont().getStyle() & ~java.awt.Font.BOLD, 11));
        caseNumberLabel.setText(org.openide.util.NbBundle.getMessage(CasePropertiesForm.class, "CasePropertiesForm.caseNumberLabel.text")); // NOI18N

        examinerLabel.setFont(examinerLabel.getFont().deriveFont(examinerLabel.getFont().getStyle() & ~java.awt.Font.BOLD, 11));
        examinerLabel.setText(org.openide.util.NbBundle.getMessage(CasePropertiesForm.class, "CasePropertiesForm.examinerLabel.text")); // NOI18N

        lbDbType.setFont(lbDbType.getFont().deriveFont(lbDbType.getFont().getStyle() & ~java.awt.Font.BOLD, 11));
        lbDbType.setText(org.openide.util.NbBundle.getMessage(CasePropertiesForm.class, "CasePropertiesForm.lbDbType.text")); // NOI18N

        lbDbName.setFont(lbDbName.getFont().deriveFont(lbDbName.getFont().getStyle() & ~java.awt.Font.BOLD, 11));
        lbDbName.setText(org.openide.util.NbBundle.getMessage(CasePropertiesForm.class, "CasePropertiesForm.lbDbName.text")); // NOI18N

        caseDirField.setMinimumSize(new java.awt.Dimension(25, 14));

        dbNameField.setMinimumSize(new java.awt.Dimension(25, 14));

        caseTypeField.setMaximumSize(new java.awt.Dimension(1, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDbName)
                            .addComponent(lbDbType)
                            .addComponent(caseDirLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(caseDirField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(caseTypeField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dbNameField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(crDateLabel)
                            .addComponent(examinerLabel)
                            .addComponent(caseNumberLabel))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(examinerField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(caseNumberField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(caseNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(updateCaseNameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(crDateField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(deleteCaseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(caseNameLabel)
                    .addContainerGap(392, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(caseNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateCaseNameButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(caseNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(examinerField, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(examinerLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(crDateField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(crDateLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(caseDirLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(caseDirField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(caseTypeField, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDbType))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbDbName)
                            .addComponent(dbNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(caseNumberLabel))
                .addGap(10, 10, 10)
                .addComponent(deleteCaseButton)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(caseNameLabel)
                    .addContainerGap(173, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Updates the case name.
     *
     * @param evt The action event
     */
    private void updateCaseNameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateCaseNameButtonActionPerformed
        String oldCaseName = Case.getCurrentCase().getName();
        String newCaseName = caseNameTextField.getText();
        // check if the old and new case name is not equal
        if (!oldCaseName.equals(newCaseName)) {

            // check if the case name is empty
            if (newCaseName.trim().equals("")) {
                JOptionPane.showMessageDialog(caller,
                        NbBundle.getMessage(this.getClass(),
                                "CasePropertiesForm.updateCaseName.msgDlg.empty.msg"),
                        NbBundle.getMessage(this.getClass(),
                                "CasePropertiesForm.updateCaseName.msgDlg.empty.title"),
                        JOptionPane.ERROR_MESSAGE);
            } else // check if case Name contain one of this following symbol:
            //  \ / : * ? " < > |
            if (newCaseName.contains("\\") || newCaseName.contains("/") || newCaseName.contains(":")
                    || newCaseName.contains("*") || newCaseName.contains("?") || newCaseName.contains("\"")
                    || newCaseName.contains("<") || newCaseName.contains(">") || newCaseName.contains("|")) {
                String errorMsg = NbBundle
                        .getMessage(this.getClass(), "CasePropertiesForm.updateCaseName.msgDlg.invalidSymbols.msg");
                JOptionPane.showMessageDialog(caller, errorMsg,
                        NbBundle.getMessage(this.getClass(),
                                "CasePropertiesForm.updateCaseName.msgDlg.invalidSymbols.title"),
                        JOptionPane.ERROR_MESSAGE);
            } else {
                // ask for the confirmation first
                String confMsg = NbBundle
                        .getMessage(this.getClass(), "CasePropertiesForm.updateCaseName.confMsg.msg", oldCaseName,
                                newCaseName);
                NotifyDescriptor d = new NotifyDescriptor.Confirmation(confMsg,
                        NbBundle.getMessage(this.getClass(),
                                "CasePropertiesForm.updateCaseName.confMsg.title"),
                        NotifyDescriptor.YES_NO_OPTION, NotifyDescriptor.WARNING_MESSAGE);
                d.setValue(NotifyDescriptor.NO_OPTION);
                
                Object res = DialogDisplayer.getDefault().notify(d);
                if (res != null && res == DialogDescriptor.YES_OPTION) {
                    // if user select "Yes"
                    String oldPath = current.getCaseMetadata().getFilePath().toString();
                    try {
                        current.updateCaseName(oldCaseName, oldPath, newCaseName, oldPath);
                    } catch (Exception ex) {
                        Logger.getLogger(CasePropertiesForm.class.getName()).log(Level.WARNING, "Error: problem updating case name.", ex); //NON-NLS
                    }
                }
            }
        }
    }//GEN-LAST:event_updateCaseNameButtonActionPerformed

    private void deleteCaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteCaseButtonActionPerformed
        CallableSystemAction.get(DeleteCurrentCaseAction.class).actionPerformed(evt);
    }//GEN-LAST:event_deleteCaseButtonActionPerformed

    private void caseNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caseNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_caseNameTextFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel caseDirField;
    private javax.swing.JLabel caseDirLabel;
    private javax.swing.JLabel caseNameLabel;
    private javax.swing.JTextField caseNameTextField;
    private javax.swing.JLabel caseNumberField;
    private javax.swing.JLabel caseNumberLabel;
    private javax.swing.JLabel caseTypeField;
    private javax.swing.JLabel crDateField;
    private javax.swing.JLabel crDateLabel;
    private javax.swing.JLabel dbNameField;
    private javax.swing.JButton deleteCaseButton;
    private javax.swing.JLabel examinerField;
    private javax.swing.JLabel examinerLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lbDbName;
    private javax.swing.JLabel lbDbType;
    private javax.swing.JButton updateCaseNameButton;
    // End of variables declaration//GEN-END:variables

}
