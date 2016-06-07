package mx.skylabs.petagram;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;
import java.util.Properties;
import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Contacto extends AppCompatActivity {

    private EditText tiNombre;
    private EditText tiCorreo;
    private EditText tiMensaje;
    private Button btnEnviar;
    private Session session;
    ProgressDialog progressDialog = null;
    Context context = null;


    /* TODO - se tiene que activar el acceso a aplicaciones menos seguras desde el siguiente link:
        TODO - https://www.google.com/settings/security/lesssecureapps */

    private final String miCuenta = "miCuenta@gmail.com";
    private final String miPassword = "miPassword";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        tiNombre = (EditText)findViewById(R.id.tiNombre);
        tiCorreo = (EditText)findViewById(R.id.tiCorreo);
        tiMensaje = (EditText)findViewById(R.id.tiMensaje);
        btnEnviar = (Button)findViewById(R.id.btnEnviar);

        context = this;
    }


    public void enviarCorreo(View view){

        Properties props = new Properties();

        /*
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.quitwait", "false");
        */

        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.auth","true");
        props.setProperty("mail.smtp.port", "465");


        //Session session = Session.getDefaultInstance(props);
        //Session session = Session.getInstance(props, null);

        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    //Authenticating the password
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(miCuenta, miPassword);
                    }
                });



        progressDialog = ProgressDialog.show(context,"","Enviando Email...",true);

        RetrieveFeedbackTask task = new RetrieveFeedbackTask();

        task.execute(tiNombre.getText().toString(),null,tiMensaje.getText().toString());

        /*
        session.setDebug(true);


        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom( new InternetAddress("dddhhhaaaxxx@gmail.com") ); //aqui va el correo
            //msg.setRecipients(Message.RecipientType.TO,"danielreyesicc@gmail.com");
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress("danielreyesicc@gmail.com"));
            msg.setSubject("Aqui va el Asunto");
            msg.setText("Wololo"); //aqui va el mensaje
            Transport.send(msg);
        } catch (MessagingException mex) {
            System.out.println("send failed, exception: " + mex);
        }

        */


    }



    class RetrieveFeedbackTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {

            try{
                Message message = new MimeMessage(session);

                message.setFrom(new InternetAddress(miCuenta));
                message.setRecipient(Message.RecipientType.TO, new InternetAddress("danielreyesicc@gmail.com"));
                message.setSubject(params[0]);
                message.setContent(params[2],"text/html; charset=utf-8");
                Transport.send(message);

            } catch(Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(),"Mensaje Enviado",Toast.LENGTH_LONG).show();
        }


    }


}
