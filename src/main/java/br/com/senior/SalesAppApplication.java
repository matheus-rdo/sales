package br.com.senior;

import br.com.senior.view.LoginFrame;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SalesAppApplication {

    public static void main(String[] args) {
        startUISettings();
        ConfigurableApplicationContext context = new SpringApplicationBuilder(SalesAppApplication.class)
                .headless(false).run(args);
        LoginFrame appFrame = context.getBean(LoginFrame.class);
        appFrame.setVisible(true);
    }

    private static void startUISettings() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
