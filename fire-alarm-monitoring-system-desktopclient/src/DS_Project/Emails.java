/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DS_Project;

/**
 *
 * @author ACER
 */
public class Emails {
    private boolean sendEmail;
    
    public Emails(boolean sendEmail){
        this.sendEmail = sendEmail;
    }
    
    public boolean isSendEmail() {
        return sendEmail;
    }

    public void setSEndEmail(boolean sendEmail) {
        this.sendEmail = sendEmail;
    }
}
