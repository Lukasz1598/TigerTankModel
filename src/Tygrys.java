
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.glsl.ShaderCode;
import com.jogamp.opengl.util.glsl.ShaderProgram;
import com.jogamp.opengl.util.glsl.ShaderState;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class Tygrys extends JFrame implements GLEventListener {

	private GL2 gl;
	private GLU glu;
	private GLUT glut;
	private FPSAnimator animator;
	private float kat=0.0f;
	private float obrotWiezy = 0.0f;
	private float obrotLufy = 0.0f;
	private float obrotModeluPoziom;
	private int texture,texture1,texture2,texture3;
	private ShaderState st;
	private float alfa,beta,charlie,delta;
	private Boolean keys[]=new Boolean[256];
	
	public Tygrys(String string) {
		super(string);
		GLProfile profile=GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities=new GLCapabilities(profile);
		GLCanvas canvas=new GLCanvas(capabilities);
		canvas.addGLEventListener(this);
		add(canvas);
		
		setSize(800,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		alfa=0.0f; beta=0.0f;
		
		for(int i=0;i<256;i++) keys[i]=false;
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				keys[e.getKeyCode()]=false;
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				keys[e.getKeyCode()]=true;
			}
		});
		animator=new FPSAnimator(canvas,60);
		animator.start();
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		ObslugaKlawiatury();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glDisable(GL2.GL_DEPTH_TEST);
		gl.glDisable(GL2.GL_LIGHTING);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glPushMatrix();
		gl.glLoadIdentity();
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glBindTexture(GL2.GL_TEXTURE_2D, texture3);
		gl.glTexParameterf(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT);
		gl.glTexParameterf(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_T, GL2.GL_REPEAT);
		gl.glMatrixMode(GL2.GL_TEXTURE);
		gl.glLoadIdentity();
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glBegin(GL2.GL_QUADS);
		gl.glTexCoord2f(0.0f,1.0f); gl.glVertex3f(-1.0f,-1.0f,-1.0f);
		gl.glTexCoord2f(1.0f,1.0f); gl.glVertex3f( 1.0f,-1.0f,-1.0f);
		gl.glTexCoord2f(1.0f,0.0f); gl.glVertex3f( 1.0f, 1.0f,-1.0f);
		gl.glTexCoord2f(0.0f,0.0f); gl.glVertex3f(-1.0f, 1.0f,-1.0f);
		gl.glEnd();
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glPopMatrix();
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		//gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		
		
		gl.glLoadIdentity();
		gl.glRotatef(20, 1.0f, 0.0f, 0.0f);
		gl.glRotatef(obrotModeluPoziom, 0.0f, 1.0f, 0.0f);

		//gl.glRotatef(kat, 0.0f, 0.0f, 1.0f);
		
		
		gl.glPushMatrix();
		gl.glPushMatrix();
		gl.glBindTexture(GL2.GL_TEXTURE_2D, texture);
		gl.glTexParameterf(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, GL2.GL_CLAMP);
		gl.glTexParameterf(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_T, GL2.GL_CLAMP);
		gl.glMatrixMode(GL2.GL_TEXTURE);
		gl.glLoadIdentity();
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		
		/////////////////////////////// wie¿a //////////////////////////////////////
		gl.glPushMatrix();//góra
		gl.glRotatef(obrotWiezy, 0.0f, 1.0f, 0.0f);
		gl.glTranslatef(0f, 0, 0.5f);

		gl.glPushMatrix(); //wie¿a
		
		//Wie¿a_top
		gl.glBegin(GL2.GL_QUAD_STRIP);
		gl.glTexCoord2f(0.2646f,0.6982f); gl.glVertex3f(-0.35f,0.30f,0.0f);	//1g
		gl.glTexCoord2f(0.4102f,0.6980f); gl.glVertex3f(0.35f,0.30f,0.0f); 	//2g
		gl.glTexCoord2f(0.2378f,0.7729f); gl.glVertex3f(-0.47f,0.35f,-0.35f);//3g
		gl.glTexCoord2f(0.4365f,0.7729f); gl.glVertex3f(0.47f,0.35f,-0.35f);//4g
		gl.glTexCoord2f(0.2329f,0.8042f); gl.glVertex3f(-0.5f,0.35f,-0.5f);	//5g
		gl.glTexCoord2f(0.4409f,0.8042f); gl.glVertex3f(0.5f,0.35f,-0.5f);	//6g
		gl.glTexCoord2f(0.2349f,0.8252f); gl.glVertex3f(-0.49f,0.35f,-0.6f);//7g
		gl.glTexCoord2f(0.4395f,0.8252f); gl.glVertex3f(0.49f,0.35f,-0.6f);	//8g
		gl.glTexCoord2f(0.2412f,0.8462f); gl.glVertex3f(-0.45f,0.35f,-0.7f);//9g
		gl.glTexCoord2f(0.4326f,0.8462f); gl.glVertex3f(0.45f,0.35f,-0.7f);	//10g
		gl.glTexCoord2f(0.2539f,0.8672f); gl.glVertex3f(-0.4f,0.35f,-0.8f);	//11g
		gl.glTexCoord2f(0.4214f,0.8672f); gl.glVertex3f(0.4f,0.35f,-0.8f);	//12g
		gl.glTexCoord2f(0.2749f,0.8882f); gl.glVertex3f(-0.3f,0.35f,-0.9f);	//13g
		gl.glTexCoord2f(0.3999f,0.8882f); gl.glVertex3f(0.3f,0.35f,-0.9f);	//14g
		gl.glTexCoord2f(0.3169f,0.9072f); gl.glVertex3f(-0.1f,0.35f,-0.97f);//15g
		gl.glTexCoord2f(0.3589f,0.9072f); gl.glVertex3f(0.1f,0.35f,-0.97f);	//16g
		gl.glEnd();
		
		//Wie¿a_sciana_przednia
		gl.glBegin(GL2.GL_QUADS);
		gl.glTexCoord2f(0.2646f,0.6982f); gl.glVertex3f(-0.35f,0.30f,0.0f);	//1
		gl.glTexCoord2f(0.2646f,0.6436f); gl.glVertex3f(-0.35f,0.05f,0.0f);
		gl.glTexCoord2f(0.4102f,0.6436f); gl.glVertex3f(0.35f,0.05f,0.0f);
		gl.glTexCoord2f(0.4102f,0.6980f); gl.glVertex3f(0.35f,0.30f,0.0f); 	//4
		gl.glEnd();
		
		//Wie¿a_sciana
		gl.glBegin(GL2.GL_QUAD_STRIP);
		gl.glTexCoord2f(0.5684f,0.9365f); gl.glVertex3f(-0.35f,0.05f,0.0f);	//1d
		gl.glTexCoord2f(0.5684f,0.9902f); gl.glVertex3f(-0.35f,0.30f,0.0f);	//1g
		gl.glTexCoord2f(0.4932f,0.9365f); gl.glVertex3f(-0.47f,0.05f,-0.35f);//3d_a
		gl.glTexCoord2f(0.4932f,1.0f); gl.glVertex3f(-0.47f,0.35f,-0.35f); 	//3g_a
		gl.glTexCoord2f(0.4932f,0.9263f); gl.glVertex3f(-0.47f,0.0f,-0.35f);//3d_b
		gl.glTexCoord2f(0.4932f,1.0f);gl.glVertex3f(-0.47f,0.35f,-0.35f); 	//3g_b
		gl.glTexCoord2f(0.4590f,0.9263f); gl.glVertex3f(-0.5f,0.0f,-0.5f);	//5d
		gl.glTexCoord2f(0.4590f,1.0f);gl.glVertex3f(-0.5f,0.35f,-0.5f);		//5g
		gl.glTexCoord2f(0.4385f,0.9263f); gl.glVertex3f(-0.49f,0.0f,-0.6f); //7d
		gl.glTexCoord2f(0.4385f,1.0f);gl.glVertex3f(-0.49f,0.35f,-0.6f);  	//7g
		gl.glTexCoord2f(0.4160f,0.9263f); gl.glVertex3f(-0.45f,0.0f,-0.7f);	//9d
		gl.glTexCoord2f(0.4160f,1.0f);gl.glVertex3f(-0.45f,0.35f,-0.7f);	//9g
		gl.glTexCoord2f(0.3926f,0.9263f); gl.glVertex3f(-0.4f,0.0f,-0.8f);	//11d
		gl.glTexCoord2f(0.3926f,1.0f);gl.glVertex3f(-0.4f,0.35f,-0.8f);		//11g
		gl.glTexCoord2f(0.3628f,0.9263f); gl.glVertex3f(-0.3f,0.0f,-0.9f);	//13d
		gl.glTexCoord2f(0.3628f,1.0f);gl.glVertex3f(-0.3f,0.35f,-0.9f);		//13g
		gl.glTexCoord2f(0.3179f,0.9263f); gl.glVertex3f(-0.1f,0.0f,-0.97f);	//15d
		gl.glTexCoord2f(0.3179f,1.0f);gl.glVertex3f(-0.1f,0.35f,-0.97f);	//15g
		gl.glTexCoord2f(0.2705f,0.9263f); gl.glVertex3f(0.1f,0.0f,-0.97f);	//16d
		gl.glTexCoord2f(0.2705f,1.0f);gl.glVertex3f(0.1f,0.35f,-0.97f);		//16g
		gl.glTexCoord2f(0.2256f,0.9263f); gl.glVertex3f(0.3f,0.0f,-0.9f);	//14d
		gl.glTexCoord2f(0.2256f,1.0f);gl.glVertex3f(0.3f,0.35f,-0.9f);		//14g
		gl.glTexCoord2f(0.1958f,0.9263f); gl.glVertex3f(0.4f,0.0f,-0.8f);	//12d
		gl.glTexCoord2f(0.1958f,1.0f);gl.glVertex3f(0.4f,0.35f,-0.8f);		//12g
		gl.glTexCoord2f(0.1724f,0.9263f); gl.glVertex3f(0.45f,0.0f,-0.7f);	//10d
		gl.glTexCoord2f(0.1724f,1.0f);gl.glVertex3f(0.45f,0.35f,-0.7f);		//10g
		gl.glTexCoord2f(0.1499f,0.9263f); gl.glVertex3f(0.49f,0.0f,-0.6f);	//8d
		gl.glTexCoord2f(0.1499f,1.0f);gl.glVertex3f(0.49f,0.35f,-0.6f);		//8g
		gl.glTexCoord2f(0.1294f,0.9263f); gl.glVertex3f(0.5f,0.0f,-0.5f);	//6d
		gl.glTexCoord2f(0.1294f,1.0f);gl.glVertex3f(0.5f,0.35f,-0.5f);		//6g
		gl.glTexCoord2f(0.0952f,0.9263f); gl.glVertex3f(0.47f,0.0f,-0.35f);	//4d_a
		gl.glTexCoord2f(0.0952f,1.0f);gl.glVertex3f(0.47f,0.35f,-0.35f);	//4g_a
		gl.glTexCoord2f(0.0952f,0.9365f); gl.glVertex3f(0.47f,0.05f,-0.35f);//4d_b
		gl.glTexCoord2f(0.0952f,1.0f);gl.glVertex3f(0.47f,0.35f,-0.35f);	//4g_b
		gl.glTexCoord2f(0.0195f,0.9365f); gl.glVertex3f(0.35f,0.05f,0.0f); 	//2d
		gl.glTexCoord2f(0.0195f,0.9902f);gl.glVertex3f(0.35f,0.30f,0.0f); 	//2g
		gl.glEnd();
		
		//wie¿a_kosz_góra
		gl.glBegin(GL2.GL_QUAD_STRIP);
		gl.glTexCoord2f(0.1567f,0.7505f); gl.glVertex3f(-0.3f,0.34f,-1.05f);	//1
		gl.glTexCoord2f(0.1479f,0.7803f); gl.glVertex3f(-0.25f,0.34f,-0.92f);	//2
		gl.glTexCoord2f(0.1294f,0.7402f); gl.glVertex3f(-0.12f,0.34f,-1.14f);	//3
		gl.glTexCoord2f(0.1226f,0.7803f); gl.glVertex3f(-0.1f,0.34f,-0.92f);	//4
		gl.glTexCoord2f(0.0967f,0.7368f); gl.glVertex3f(0.0f,0.34f,-1.15f);	//5
		gl.glTexCoord2f(0.0967f,0.7803f); gl.glVertex3f(0.0f,0.34f,-0.92f);	//6
		gl.glTexCoord2f(0.0659f,0.7402f); gl.glVertex3f(0.12f,0.34f,-1.14f);	//7
		gl.glTexCoord2f(0.0693f,0.7803f); gl.glVertex3f(0.1f,0.34f,-0.92f);	//8
		gl.glTexCoord2f(0.0391f,0.7505f); gl.glVertex3f(0.3f,0.34f,-1.05f);	//9
		gl.glTexCoord2f(0.0474f,0.7803f); gl.glVertex3f(0.25f,0.34f,-0.92f);	//10
		gl.glEnd();
		
		//wie¿a_kosz_sciana
		gl.glBegin(GL2.GL_QUAD_STRIP);
		gl.glTexCoord2f(0.0063f,0.7178f); gl.glVertex3f(0.25f,0.34f,-0.92f);	//10g
		gl.glTexCoord2f(0.0063f,0.6631f); gl.glVertex3f(0.25f,0.07f,-0.92f);	//10d
		gl.glTexCoord2f(0.0386f,0.7178f); gl.glVertex3f(0.3f,0.34f,-1.05f);	//9g
		gl.glTexCoord2f(0.0386f,0.6631f); gl.glVertex3f(0.3f,0.07f,-1.05f);	//9d
		gl.glTexCoord2f(0.0698f,0.7178f); gl.glVertex3f(0.12f,0.34f,-1.14f);	//7g
		gl.glTexCoord2f(0.0698f,0.6631f); gl.glVertex3f(0.12f,0.07f,-1.14f);	//7d
		gl.glTexCoord2f(0.1006f,0.7178f); gl.glVertex3f(0.0f,0.34f,-1.15f);	//5g
		gl.glTexCoord2f(0.1006f,0.6631f); gl.glVertex3f(0.0f,0.07f,-1.15f);	//5d
		gl.glTexCoord2f(0.1309f,0.7178f); gl.glVertex3f(-0.12f,0.34f,-1.14f);	//3g
		gl.glTexCoord2f(0.1309f,0.6631f); gl.glVertex3f(-0.12f,0.07f,-1.14f);	//3d
		gl.glTexCoord2f(0.1616f,0.7178f); gl.glVertex3f(-0.3f,0.34f,-1.05f);	//1g
		gl.glTexCoord2f(0.1616f,0.6631f); gl.glVertex3f(-0.3f,0.07f,-1.05f);	//1d
		gl.glTexCoord2f(0.1929f,0.7178f); gl.glVertex3f(-0.25f,0.34f,-0.92f);	//2g
		gl.glTexCoord2f(0.1929f,0.6631f); gl.glVertex3f(-0.25f,0.07f,-0.92f);	//2d
		gl.glEnd();		
		gl.glPopMatrix(); //wie¿a koniec
		
		
		gl.glPushMatrix();//dzia³o
		gl.glTranslatef(0f, 0, 0.0f);
		gl.glTranslatef(0f, 0.15f,0f);
		gl.glRotatef(obrotLufy, 1.0f, 0.0f, 0.0f);
		gl.glTranslatef(0f, 0.0f,-0.05f);
		gl.glTranslatef(0f, -0.125f,0f);
		//jarzmo 
		gl.glBegin(GL2.GL_QUAD_STRIP);
		gl.glTexCoord2f(0.0f,0.8730f); gl.glVertex3f(-0.32f,0.225f,0.0f);	//1
		gl.glTexCoord2f(0.0f,0.8477f); gl.glVertex3f(-0.32f,0.05f,0.0f);		//2
		gl.glTexCoord2f(0.0103f,0.8730f); gl.glVertex3f(-0.32f,0.225f,0.075f);	//3
		gl.glTexCoord2f(0.0103f,0.8477f); gl.glVertex3f(-0.32f,0.05f,0.075f);	//4
		gl.glTexCoord2f(0.0181f,0.8828f); gl.glVertex3f(-0.32f,0.25f,0.1f);		//5
		gl.glTexCoord2f(0.0181f,0.8374f); gl.glVertex3f(-0.32f,0.025f,0.1f);	//6
		gl.glTexCoord2f(0.0317f,0.8828f); gl.glVertex3f(-0.32f,0.25f,0.15f);	//7
		gl.glTexCoord2f(0.0317f,0.8374f); gl.glVertex3f(-0.32f,0.025f,0.15f);	//8
		gl.glTexCoord2f(0.1494f,0.8828f); gl.glVertex3f(0.32f,0.25f,0.15f);		//9
		gl.glTexCoord2f(0.1494f,0.8374f); gl.glVertex3f(0.32f,0.025f,0.15f);	//10
		gl.glTexCoord2f(0.1631f,0.8823f); gl.glVertex3f(0.32f,0.25f,0.1f);		//11
		gl.glTexCoord2f(0.1631f,0.8374f); gl.glVertex3f(0.32f,0.025f,0.1f);		//12
		gl.glTexCoord2f(0.1704f,0.8730f); gl.glVertex3f(0.32f,0.225f,0.075f);	//13
		gl.glTexCoord2f(0.1704f,0.8477f); gl.glVertex3f(0.32f,0.05f,0.075f);	//14
		gl.glTexCoord2f(0.1807f,0.8730f); gl.glVertex3f(0.32f,0.225f,0.0f);		//15
		gl.glTexCoord2f(0.1807f,0.8477f); gl.glVertex3f(0.32f,0.05f,0.0f);		//16
		gl.glEnd();
		
		//jarzmo_góra
		gl.glBegin(GL2.GL_QUAD_STRIP);
		gl.glTexCoord2f(0.1499f,0.9185f); gl.glVertex3f(0.32f,0.225f,0.0f);		//15
		gl.glTexCoord2f(0.0317f,0.9185f); gl.glVertex3f(-0.32f,0.225f,0.0f);	//1
		gl.glTexCoord2f(0.1499f,0.9048f); gl.glVertex3f(0.32f,0.225f,0.075f);	//13
		gl.glTexCoord2f(0.0317f,0.9048f); gl.glVertex3f(-0.32f,0.225f,0.075f);	//3
		gl.glTexCoord2f(0.1499f,0.8960f); gl.glVertex3f(0.32f,0.25f,0.1f);		//11
		gl.glTexCoord2f(0.0317f,0.8960f); gl.glVertex3f(-0.32f,0.25f,0.1f);		//5
		gl.glTexCoord2f(0.1499f,0.8828f); gl.glVertex3f(0.32f,0.25f,0.15f);		//9
		gl.glTexCoord2f(0.0317f,0.8828f); gl.glVertex3f(-0.32f,0.25f,0.15f);	//7
		gl.glEnd();
		
		//jarzmo_dó³
		gl.glBegin(GL2.GL_QUAD_STRIP);
		gl.glTexCoord2f(0.1499f,0.8013f); gl.glVertex3f(-0.32f,0.05f,0.0f);	//2
		gl.glTexCoord2f(0.0317f,0.8013f); gl.glVertex3f(0.32f,0.05f,0.0f);	//16
		gl.glTexCoord2f(0.1499f,0.8169f); gl.glVertex3f(-0.32f,0.05f,0.075f);	//4
		gl.glTexCoord2f(0.0317f,0.8169f); gl.glVertex3f(0.32f,0.05f,0.075f);	//14
		gl.glTexCoord2f(0.1499f,0.8276f); gl.glVertex3f(-0.32f,0.025f,0.1f);	//6
		gl.glTexCoord2f(0.0317f,0.8276f); gl.glVertex3f(0.32f,0.025f,0.1f);	//12
		gl.glTexCoord2f(0.1499f,0.8374f); gl.glVertex3f(-0.32f,0.025f,0.15f);	//8
		gl.glTexCoord2f(0.0317f,0.8374f); gl.glVertex3f(0.32f,0.025f,0.15f);	//10
		gl.glEnd();
		
		//lufa
		gl.glTranslatef(0f, 0.05f,0.15f);
		
		//lufa_1
		gl.glBegin(GL2.GL_QUAD_STRIP);
		gl.glTexCoord2f(0.7559f,0.9585f); gl.glVertex3f(0.0f,0.0f,0.0f);	//1
		gl.glTexCoord2f(1.0f,0.9585f); gl.glVertex3f(0.0f,0.0f,0.5f);		//2
		gl.glTexCoord2f(0.7559f,0.9634f); gl.glVertex3f(-0.05f,0.0f,0.0f);	//3
		gl.glTexCoord2f(1.0f,0.9634f); gl.glVertex3f(-0.05f,0.0f,0.5f);		//4
		gl.glTexCoord2f(0.7559f,0.9683f); gl.glVertex3f(-0.1f,0.05f,0.0f);	//5
		gl.glTexCoord2f(1.0f,0.9683f); gl.glVertex3f(-0.1f,0.05f,0.5f);		//6
		gl.glTexCoord2f(0.7559f,0.9741f); gl.glVertex3f(-0.1f,0.1f,0.0f);	//7
		gl.glTexCoord2f(1.0f,0.9741f); gl.glVertex3f(-0.1f,0.1f,0.5f);		 //8
		gl.glTexCoord2f(0.7559f,0.9795f); gl.glVertex3f(-0.05f,0.15f,0.0f);	//9
		gl.glTexCoord2f(1.0f,0.9795f); gl.glVertex3f(-0.05f,0.15f,0.5f);	 //10
		gl.glTexCoord2f(0.7559f,0.9844f); gl.glVertex3f(0.0f,0.15f,0.0f);	//11
		gl.glTexCoord2f(1.0f,0.9844f); gl.glVertex3f(0.0f,0.15f,0.5f);		 //12
		gl.glTexCoord2f(0.7559f,0.9897f); gl.glVertex3f(0.05f,0.1f,0.0f);	//13
		gl.glTexCoord2f(1.0f,0.9897f); gl.glVertex3f(0.05f,0.1f,0.5f);		 //14
		gl.glTexCoord2f(0.7559f,0.9946f); gl.glVertex3f(0.05f,0.05f,0.0f);	//15
		gl.glTexCoord2f(1.0f,0.9946f); gl.glVertex3f(0.05f,0.05f,0.5f);		 //16
		gl.glTexCoord2f(0.7559f,1.0f); gl.glVertex3f(0.0f,0.0f,0.0f);		//17
		gl.glTexCoord2f(1.0f,1.0f); gl.glVertex3f(0.0f,0.0f,0.5f);			 //18
		gl.glEnd();
		
		//lufa_³¹czenie_1
		gl.glBegin(GL2.GL_QUAD_STRIP);
		gl.glTexCoord2f(0.7831f,0.9805f); gl.glVertex3f(0.0f,0.0f,0.5f);	//1
		gl.glTexCoord2f(0.7831f,0.9805f); gl.glVertex3f(0.0f,0.02f,0.5f);	//2
		gl.glTexCoord2f(0.7831f,0.9805f); gl.glVertex3f(-0.05f,0.0f,0.5f);	//3
		gl.glTexCoord2f(0.7831f,0.9805f); gl.glVertex3f(-0.05f,0.02f,0.5f);//4
		gl.glTexCoord2f(0.7831f,0.9805f); gl.glVertex3f(-0.1f,0.05f,0.5f);	//5
		gl.glTexCoord2f(0.7831f,0.9805f); gl.glVertex3f(-0.08f,0.05f,0.5f);	//6
		gl.glTexCoord2f(0.7831f,0.9805f); gl.glVertex3f(-0.1f,0.1f,0.5f);	//7
		gl.glTexCoord2f(0.7831f,0.9805f); gl.glVertex3f(-0.08f,0.1f,0.5f);	//8
		gl.glTexCoord2f(0.7831f,0.9805f); gl.glVertex3f(-0.05f,0.15f,0.5f);	//9
		gl.glTexCoord2f(0.7831f,0.9805f); gl.glVertex3f(-0.05f,0.13f,0.5f);	//10
		gl.glTexCoord2f(0.7831f,0.9805f); gl.glVertex3f(0.0f,0.15f,0.5f);	//11
		gl.glTexCoord2f(0.7831f,0.9805f); gl.glVertex3f(0.0f,0.13f,0.5f);	//12
		gl.glTexCoord2f(0.7831f,0.9805f); gl.glVertex3f(0.05f,0.1f,0.5f);	//13
		gl.glTexCoord2f(0.7831f,0.9805f); gl.glVertex3f(0.03f,0.1f,0.5f);		//14
		gl.glTexCoord2f(0.7831f,0.9805f); gl.glVertex3f(0.05f,0.05f,0.5f);	//15
		gl.glTexCoord2f(0.7831f,0.9805f); gl.glVertex3f(0.03f,0.05f,0.5f);		//16
		gl.glTexCoord2f(0.7831f,0.9805f); gl.glVertex3f(0.0f,0.0f,0.5f);		//17
		gl.glTexCoord2f(0.7831f,0.9805f); gl.glVertex3f(0.0f,0.02f,0.5f);			//18
		gl.glEnd();
		
		//lufa_2
		gl.glBegin(GL2.GL_QUAD_STRIP);
		gl.glTexCoord2f(0.7559f,0.9585f); gl.glVertex3f(0.0f,0.02f,0.5f);	//1
		gl.glTexCoord2f(1.0f,0.9585f); gl.glVertex3f(0.0f,0.02f,1.0f);		//2
		gl.glTexCoord2f(0.7559f,0.9634f); gl.glVertex3f(-0.05f,0.02f,0.5f);	//3
		gl.glTexCoord2f(1.0f,0.9634f); gl.glVertex3f(-0.05f,0.02f,1.0f);	//4
		gl.glTexCoord2f(0.7559f,0.9683f); gl.glVertex3f(-0.08f,0.05f,0.5f);	//5
		gl.glTexCoord2f(1.0f,0.9683f); gl.glVertex3f(-0.08f,0.05f,1.0f);	//6
		gl.glTexCoord2f(0.7559f,0.9741f); gl.glVertex3f(-0.08f,0.1f,0.5f);	//7
		gl.glTexCoord2f(1.0f,0.9741f); gl.glVertex3f(-0.08f,0.1f,1.0f);		//8
		gl.glTexCoord2f(0.7559f,0.9795f); gl.glVertex3f(-0.05f,0.13f,0.5f);	//9
		gl.glTexCoord2f(1.0f,0.9795f); gl.glVertex3f(-0.05f,0.13f,1.0f);	//10
		gl.glTexCoord2f(0.7559f,0.9844f); gl.glVertex3f(0.0f,0.13f,0.5f);	//11
		gl.glTexCoord2f(1.0f,0.9844f); gl.glVertex3f(0.0f,0.13f,1.0f);		//12
		gl.glTexCoord2f(0.7559f,0.9897f); gl.glVertex3f(0.03f,0.1f,0.5f);	//13
		gl.glTexCoord2f(1.0f,0.9897f); gl.glVertex3f(0.03f,0.1f,1.0f);		//14
		gl.glTexCoord2f(0.7559f,0.9946f); gl.glVertex3f(0.03f,0.05f,0.5f);	//15
		gl.glTexCoord2f(1.0f,0.9946f); gl.glVertex3f(0.03f,0.05f,1.0f);		//16
		gl.glTexCoord2f(0.7559f,1.0f); gl.glVertex3f(0.0f,0.02f,0.5f);		//17
		gl.glTexCoord2f(1.0f,1.0f); gl.glVertex3f(0.0f,0.02f,1.0f);			//18
		gl.glEnd();
		
		//lufa_³¹czenie_2
		gl.glBegin(GL2.GL_QUAD_STRIP);
		gl.glTexCoord2f(0.7831f,0.9805f); gl.glVertex3f(0.0f,0.02f,1.0f);	//1
		gl.glTexCoord2f(0.7831f,0.9805f); gl.glVertex3f(0.0f,0.04f,1.0f);		//2
		gl.glTexCoord2f(0.7831f,0.9805f); gl.glVertex3f(-0.05f,0.02f,1.0f);	//3
		gl.glTexCoord2f(0.7831f,0.9805f); gl.glVertex3f(-0.05f,0.04f,1.0f);	//4
		gl.glTexCoord2f(0.7831f,0.9805f); gl.glVertex3f(-0.08f,0.05f,1.0f);	//5
		gl.glTexCoord2f(0.7831f,0.9805f); gl.glVertex3f(-0.06f,0.05f,1.0f);	//6
		gl.glTexCoord2f(0.7831f,0.9805f); gl.glVertex3f(-0.08f,0.1f,1.0f);	//7
		gl.glTexCoord2f(0.7831f,0.9805f); gl.glVertex3f(-0.06f,0.1f,1.0f);		//8
		gl.glTexCoord2f(0.7831f,0.9805f); gl.glVertex3f(-0.05f,0.13f,1.0f);//9
		gl.glTexCoord2f(0.7831f,0.9805f); gl.glVertex3f(-0.05f,0.11f,1.0f);	//10
		gl.glTexCoord2f(0.7831f,0.9805f); gl.glVertex3f(0.0f,0.13f,1.0f);	//11
		gl.glTexCoord2f(0.7831f,0.9805f); gl.glVertex3f(0.0f,0.11f,1.0f);		//12
		gl.glTexCoord2f(0.7831f,0.9805f); gl.glVertex3f(0.03f,0.1f,1.0f);	//13
		gl.glTexCoord2f(0.7831f,0.9805f); gl.glVertex3f(0.01f,0.1f,1.0f);		//14
		gl.glTexCoord2f(0.7831f,0.9805f); gl.glVertex3f(0.03f,0.05f,1.0f);	//15
		gl.glTexCoord2f(0.7831f,0.9805f); gl.glVertex3f(0.01f,0.05f,1.0f);		//16
		gl.glTexCoord2f(0.7831f,0.9805f); gl.glVertex3f(0.0f,0.02f,1.0f);		//17
		gl.glTexCoord2f(0.7831f,0.9805f); gl.glVertex3f(0.0f,0.04f,1.0f);			//18
		gl.glEnd();
		
		//lufa_3
		gl.glBegin(GL2.GL_QUAD_STRIP);
		gl.glTexCoord2f(0.7559f,0.9585f); gl.glVertex3f(0.0f,0.04f,1.0f);	//1
		gl.glTexCoord2f(1.0f,0.9585f); gl.glVertex3f(0.0f,0.04f,2.0f);		//2
		gl.glTexCoord2f(0.7559f,0.9634f); gl.glVertex3f(-0.05f,0.04f,1.0f);	//3
		gl.glTexCoord2f(1.0f,0.9634f); gl.glVertex3f(-0.05f,0.04f,2.0f);	//4
		gl.glTexCoord2f(0.7559f,0.9683f); gl.glVertex3f(-0.06f,0.05f,1.0f);	//5
		gl.glTexCoord2f(1.0f,0.9683f); gl.glVertex3f(-0.06f,0.05f,2.0f);	//6
		gl.glTexCoord2f(0.7559f,0.9741f); gl.glVertex3f(-0.06f,0.1f,1.0f);	//7
		gl.glTexCoord2f(1.0f,0.9741f); gl.glVertex3f(-0.06f,0.1f,2.0f);		//8
		gl.glTexCoord2f(0.7559f,0.9795f); gl.glVertex3f(-0.05f,0.11f,1.0f);	//9
		gl.glTexCoord2f(1.0f,0.9795f); gl.glVertex3f(-0.05f,0.11f,2.0f);	//10
		gl.glTexCoord2f(0.7559f,0.9844f); gl.glVertex3f(0.0f,0.11f,1.0f);	//11
		gl.glTexCoord2f(1.0f,0.9844f); gl.glVertex3f(0.0f,0.11f,2.0f);		//12
		gl.glTexCoord2f(0.7559f,0.9897f); gl.glVertex3f(0.01f,0.1f,1.0f);	//13
		gl.glTexCoord2f(1.0f,0.9897f); gl.glVertex3f(0.01f,0.1f,2.0f);		//14
		gl.glTexCoord2f(0.7559f,0.9946f); gl.glVertex3f(0.01f,0.05f,1.0f);	//15
		gl.glTexCoord2f(1.0f,0.9946f); gl.glVertex3f(0.01f,0.05f,2.0f);		//16
		gl.glTexCoord2f(0.7559f,1.0f); gl.glVertex3f(0.0f,0.04f,1.0f);		//17
		gl.glTexCoord2f(1.0f,1.0f); gl.glVertex3f(0.0f,0.04f,2.0f);			//18
		gl.glEnd();
		
		//lufa_hamulec
		gl.glBegin(GL2.GL_QUAD_STRIP);
		gl.glTexCoord2f(0.0f,0.8120f); gl.glVertex3f(0.0f,0.04f,2.0f);		//1
		gl.glTexCoord2f(0.0176f,0.8120f); gl.glVertex3f(0.0f,0.0f,2.1f);	//2
		gl.glTexCoord2f(0.0f,0.8037f); gl.glVertex3f(-0.05f,0.04f,2.0f);	//3
		gl.glTexCoord2f(0.0176f,0.8037f); gl.glVertex3f(-0.05f,0.0f,2.1f);	//4
		gl.glTexCoord2f(0.0f,0.7949f); gl.glVertex3f(-0.06f,0.05f,2.0f);	//5
		gl.glTexCoord2f(0.0176f,0.7949f); gl.glVertex3f(-0.1f,0.05f,2.1f);	//6
		gl.glTexCoord2f(0.0f,0.7866f); gl.glVertex3f(-0.06f,0.1f,2.0f);		//7
		gl.glTexCoord2f(0.0176f,0.7866f); gl.glVertex3f(-0.1f,0.1f,2.1f);	//8
		gl.glTexCoord2f(0.0f,0.7778f); gl.glVertex3f(-0.05f,0.11f,2.0f);	//9
		gl.glTexCoord2f(0.0176f,0.7778f); gl.glVertex3f(-0.05f,0.15f,2.1f);	//10
		gl.glTexCoord2f(0.0f,0.7695f); gl.glVertex3f(0.0f,0.11f,2.0f);		//11
		gl.glTexCoord2f(0.0176f,0.7695f); gl.glVertex3f(0.0f,0.15f,2.1f);	//12
		gl.glTexCoord2f(0.0f,0.7607f); gl.glVertex3f(0.01f,0.1f,2.0f);		//13
		gl.glTexCoord2f(0.0176f,0.7607f); gl.glVertex3f(0.05f,0.1f,2.1f);	//14
		gl.glTexCoord2f(0.0f,0.7524f); gl.glVertex3f(0.01f,0.05f,2.0f);		//15
		gl.glTexCoord2f(0.0176f,0.7524f); gl.glVertex3f(0.05f,0.05f,2.1f);	//16
		gl.glTexCoord2f(0.0f,0.7437f); gl.glVertex3f(0.0f,0.04f,2.0f);		//17
		gl.glTexCoord2f(0.0176f,0.7437f); gl.glVertex3f(0.0f,0.0f,2.1f);	//18
		gl.glEnd();
		
		//lufa_hamulec_2
		gl.glBegin(GL2.GL_QUAD_STRIP);
		gl.glTexCoord2f(0.0f,0.8120f); gl.glVertex3f(0.0f,0.0f,2.1f);		//1
		gl.glTexCoord2f(0.0176f,0.8120f); gl.glVertex3f(0.0f,0.0f,2.2f);	//2
		gl.glTexCoord2f(0.0f,0.8037f); gl.glVertex3f(-0.05f,0.0f,2.1f);	//3
		gl.glTexCoord2f(0.0176f,0.8037f); gl.glVertex3f(-0.05f,0.0f,2.2f);	//4
		gl.glTexCoord2f(0.0f,0.7949f); gl.glVertex3f(-0.1f,0.05f,2.1f);	//5
		gl.glTexCoord2f(0.0176f,0.7949f); gl.glVertex3f(-0.1f,0.05f,2.2f);	//6
		gl.glTexCoord2f(0.0f,0.7866f); gl.glVertex3f(-0.1f,0.1f,2.1f);		//7
		gl.glTexCoord2f(0.0176f,0.7866f); gl.glVertex3f(-0.1f,0.1f,2.2f);	//8
		gl.glTexCoord2f(0.0f,0.7778f); gl.glVertex3f(-0.05f,0.15f,2.1f);	//9
		gl.glTexCoord2f(0.0176f,0.7778f); gl.glVertex3f(-0.05f,0.15f,2.2f);	//10
		gl.glTexCoord2f(0.0f,0.7695f); gl.glVertex3f(0.0f,0.15f,2.1f);		//11
		gl.glTexCoord2f(0.0176f,0.7695f); gl.glVertex3f(0.0f,0.15f,2.2f);	//12
		gl.glTexCoord2f(0.0f,0.7607f); gl.glVertex3f(0.05f,0.1f,2.1f);		//13
		gl.glTexCoord2f(0.0176f,0.7607f); gl.glVertex3f(0.05f,0.1f,2.2f);	//14
		gl.glTexCoord2f(0.0f,0.7524f); gl.glVertex3f(0.05f,0.05f,2.1f);		//15
		gl.glTexCoord2f(0.0176f,0.7524f); gl.glVertex3f(0.05f,0.05f,2.2f);	//16
		gl.glTexCoord2f(0.0f,0.7437f); gl.glVertex3f(0.0f,0.0f,2.1f);		//17
		gl.glTexCoord2f(0.0176f,0.7437f); gl.glVertex3f(0.0f,0.0f,2.2f);	//18
		gl.glEnd();
		
		
		//zatyczka
		gl.glBegin(GL2.GL_TRIANGLE_FAN);
		gl.glTexCoord2f(0.0103f,0.8252f); gl.glVertex3f(-0.025f,0.075f,2.2f);	//œrodek
		gl.glTexCoord2f(0.0103f,0.8149f); gl.glVertex3f(0.0f,0.0f,2.2f);	//18
		gl.glTexCoord2f(0.0171f,0.8184f); gl.glVertex3f(0.05f,0.05f,2.2f);	//16
		gl.glTexCoord2f(0.0200f,0.8257f); gl.glVertex3f(0.05f,0.1f,2.2f);	//14
		gl.glTexCoord2f(0.0171f,0.8330f); gl.glVertex3f(0.0f,0.15f,2.2f);	//12
		gl.glTexCoord2f(0.0103f,0.8359f); gl.glVertex3f(-0.05f,0.15f,2.2f);	//10
		gl.glTexCoord2f(0.0029f,0.8325f); gl.glVertex3f(-0.1f,0.1f,2.2f);	//8
		gl.glTexCoord2f(0.0f,0.8257f); gl.glVertex3f(-0.1f,0.05f,2.2f);	    //6
		gl.glTexCoord2f(0.0029f,0.8184f); gl.glVertex3f(-0.05f,0.0f,2.2f);	//4
		gl.glTexCoord2f(0.0103f,0.8149f); gl.glVertex3f(0.0f,0.0f,2.2f);	//2
		gl.glEnd();
		
		gl.glPopMatrix();
		
		gl.glPopMatrix();//dzia³o koniec
		
		gl.glPopMatrix();//góra koniec
		
		/////////////////////////////// kad³ub //////////////////////////////////////
		gl.glPushMatrix();//œrodek
		gl.glTranslatef(0f, 0, 0.9f);///////////////////////////////////////////////
		gl.glBegin(GL2.GL_QUAD_STRIP);
		gl.glTexCoord2f(0.2456f,0.3364f); gl.glVertex3f(-0.4f,-0.65f,0.1f); //p³yta 5
		gl.glTexCoord2f(0.2456f,0.1699f); gl.glVertex3f( 0.4f,-0.65f,0.1f);
		
		gl.glTexCoord2f(0.2793f,0.3364f); gl.glVertex3f(-0.4f,-0.58f,0.22f); //p³yta 4
		gl.glTexCoord2f(0.2793f,0.1699f); gl.glVertex3f( 0.4f,-0.58f,0.22f);
		gl.glTexCoord2f(0.3374f,0.3364f); gl.glVertex3f(-0.4f,-0.23f,0.32f);
		gl.glTexCoord2f(0.3377f,0.1699f); gl.glVertex3f( 0.4f,-0.23f,0.32f);
		
		gl.glTexCoord2f(0.3379f,0.4067f); gl.glVertex3f(-0.73f,-0.23f,0.32f); //p³yta 3
		gl.glTexCoord2f(0.3379f,0.0991f); gl.glVertex3f( 0.73f,-0.23f,0.32f);
		gl.glTexCoord2f(0.4063f,0.4067f); gl.glVertex3f(-0.73f,-0.17f,0.03f);
		gl.glTexCoord2f(0.4063f,0.0991f); gl.glVertex3f( 0.73f,-0.17f,0.03f);
		
		gl.glTexCoord2f(0.4072f,0.3916f); gl.glVertex3f(-0.65f,-0.17f,0.03f); //p³yta 2
		gl.glTexCoord2f(0.4072f,0.1147f); gl.glVertex3f( 0.65f,-0.17f,0.03f);
		
		gl.glTexCoord2f(0.4409f,0.3916f); gl.glVertex3f(-0.65f, 0.0f,0.0f); //p³yta 1
		gl.glTexCoord2f(0.4409f,0.1147f); gl.glVertex3f( 0.65f, 0.0f,0.0f);
		gl.glTexCoord2f(0.8774f,0.3916f); gl.glVertex3f(-0.65f, 0.0f,-2.1f);
		gl.glTexCoord2f(0.8774f,0.1147f); gl.glVertex3f( 0.65f, 0.0f,-2.1f);
		
		gl.glTexCoord2f(0.9380f,0.3916f); gl.glVertex3f(-0.65f,-0.34f,-2.05f); //p³yta -1
		gl.glTexCoord2f(0.9380f,0.1147f); gl.glVertex3f( 0.65f,-0.34f,-2.05f);
		
		gl.glTexCoord2f(0.9380f,0.3364f); gl.glVertex3f(-0.4f,-0.34f,-2.05f); //p³yta -2
		gl.glTexCoord2f(0.9380f,0.1699f); gl.glVertex3f( 0.4f,-0.34f,-2.05f);
		gl.glTexCoord2f(0.9800f,0.3364f); gl.glVertex3f(-0.4f,-0.58f,-2.02f);
		gl.glTexCoord2f(0.9800f,0.1699f); gl.glVertex3f( 0.4f,-0.58f,-2.02f);
		
		gl.glTexCoord2f(1.0f,0.3364f); gl.glVertex3f(-0.4f,-0.65f,-1.95f); //p³yta -3
		gl.glTexCoord2f(1.0f,0.1699f); gl.glVertex3f( 0.4f,-0.65f,-1.95f);
		gl.glEnd();
		
		gl.glBegin(GL2.GL_POLYGON);//bok lewy
		gl.glTexCoord2f(0.4521f,0.9175f); gl.glVertex3f( 0.4f, 0.0f,-2.1f);
		gl.glTexCoord2f(0.8896f,0.9175f); gl.glVertex3f( 0.4f, 0.0f, 0.0f);
		gl.glTexCoord2f(0.8945f,0.8853f); gl.glVertex3f( 0.4f,-0.17f, 0.03f);
		gl.glTexCoord2f(0.9604f,0.8721f); gl.glVertex3f( 0.4f,-0.23f, 0.32f);
		gl.glTexCoord2f(0.9375f,0.8198f); gl.glVertex3f( 0.4f,-0.58f, 0.22f);
		gl.glTexCoord2f(0.9087f,0.8032f); gl.glVertex3f( 0.4f,-0.65f, 0.1f);
		gl.glTexCoord2f(0.4795f,0.8032f); gl.glVertex3f( 0.4f,-0.65f,-1.95f);
		gl.glTexCoord2f(0.4653f,0.8169f); gl.glVertex3f( 0.4f,-0.58f,-2.02f);
		gl.glEnd();
		
		gl.glBegin(GL2.GL_POLYGON);//bok prawy
		gl.glTexCoord2f(0.4521f,0.9175f); gl.glVertex3f(-0.4f, 0.0f,-2.1f);
		gl.glTexCoord2f(0.4653f,0.8169f); gl.glVertex3f(-0.4f,-0.58f,-2.02f);
		gl.glTexCoord2f(0.4795f,0.8032f); gl.glVertex3f(-0.4f,-0.65f,-1.95f);
		gl.glTexCoord2f(0.9087f,0.8032f); gl.glVertex3f(-0.4f,-0.65f, 0.1f);
		gl.glTexCoord2f(0.9375f,0.8198f); gl.glVertex3f(-0.4f,-0.58f, 0.22f);
		gl.glTexCoord2f(0.9604f,0.8721f); gl.glVertex3f(-0.4f,-0.23f, 0.32f);
		gl.glTexCoord2f(0.8945f,0.8853f); gl.glVertex3f(-0.4f,-0.17f, 0.03f);
		gl.glTexCoord2f(0.8896f,0.9175f); gl.glVertex3f(-0.4f, 0.0f, 0.0f);
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUAD_STRIP); //b³otnik lewy
		gl.glTexCoord2f(0.8765f,0.1147f); gl.glVertex3f( 0.65f, 0.0f,-2.1f);
		gl.glTexCoord2f(0.4419f,0.1147f); gl.glVertex3f( 0.65f, 0.0f, 0.0f);
		gl.glTexCoord2f(0.8682f,0.0547f); gl.glVertex3f( 0.65f,-0.34f,-2.05f);
		gl.glTexCoord2f(0.4365f,0.0820f); gl.glVertex3f( 0.65f,-0.17f, 0.03f);
		gl.glTexCoord2f(0.8682f,0.0f); gl.glVertex3f( 0.4f,-0.34f,-2.05f);
		gl.glTexCoord2f(0.4326f,0.0278f); gl.glVertex3f( 0.4f,-0.17f, 0.03f);
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUAD_STRIP); //b³otnik prawy
		gl.glTexCoord2f(0.4419f,0.1147f); gl.glVertex3f(-0.65f, 0.0f, 0.0f);
		gl.glTexCoord2f(0.8765f,0.1147f); gl.glVertex3f(-0.65f, 0.0f,-2.1f);
		gl.glTexCoord2f(0.4365f,0.0820f); gl.glVertex3f(-0.65f,-0.17f, 0.03f);
		gl.glTexCoord2f(0.8682f,0.0547f); gl.glVertex3f(-0.65f,-0.34f,-2.05f);
		gl.glTexCoord2f(0.4326f,0.0278f); gl.glVertex3f(-0.4f,-0.17f, 0.03f);
		gl.glTexCoord2f(0.8682f,0.0f); gl.glVertex3f(-0.4f,-0.34f,-2.05f);
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUAD_STRIP); //b³otnik lewy 2
		gl.glTexCoord2f(0.9756f,0.8232f); gl.glVertex3f( 0.65f,-0.30f,-2.05f);
		gl.glTexCoord2f(0.9756f,0.3994f); gl.glVertex3f( 0.65f,-0.13f, 0.03f);
		gl.glTexCoord2f(1.0f,0.8232f); gl.glVertex3f( 0.75f,-0.36f,-2.05f);
		gl.glTexCoord2f(1.0f,0.3994f); gl.glVertex3f( 0.75f,-0.19f, 0.03f);
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUAD_STRIP); //b³otnik prawy 2
		gl.glTexCoord2f(0.9756f,0.8232f); gl.glVertex3f(-0.65f,-0.13f, 0.03f);
		gl.glTexCoord2f(0.9756f,0.3994f); gl.glVertex3f(-0.65f,-0.30f,-2.05f);
		gl.glTexCoord2f(1.0f,0.8232f); gl.glVertex3f(-0.75f,-0.19f, 0.03f);
		gl.glTexCoord2f(1.0f,0.3994f); gl.glVertex3f(-0.75f,-0.36f,-2.05f);
		
		gl.glEnd();
		
		
		gl.glPopMatrix();//dó³ koniec
		
		float wheelAr = (float)0.2;
		float wheelBr = (float)0.18;
		float wheelCr = (float)0.16;
		
		////////////////////////////////////// ko³a prawe ////////////////////////////////////////////////////
		gl.glPushMatrix();//Ko³o prawe B1
		gl.glTranslatef(-0.42f, -0.62f, -0.65f);
		gl.glRotatef(kat*2, 1.0f, 0.0f, 0.0f);
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.16f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.16f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glEnd();
		
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch2	
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(0.0f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(0.0f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(0.0f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(0.0f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(0.0f, 0.0f, wheelBr);
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUAD_STRIP); //ko³o œrodek-bok
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.16f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.0f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.16f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.0f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelBr);
		gl.glEnd();
		gl.glPopMatrix();
		
		gl.glPushMatrix();//Ko³o prawe B2
		gl.glTranslatef(-0.42f, -0.62f, -0.26f);
		gl.glRotatef(kat*2, 1.0f, 0.0f, 0.0f);
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.16f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.16f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glEnd();
		
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch2	
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(0.0f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(0.0f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(0.0f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(0.0f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(0.0f, 0.0f, wheelBr);
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUAD_STRIP); //ko³o œrodek-bok
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.16f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.0f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.16f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.0f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelBr);
		gl.glEnd();
		gl.glPopMatrix();
		
		gl.glPushMatrix();//Ko³o prawe B3
		gl.glTranslatef(-0.42f, -0.62f, 0.13f);
		gl.glRotatef(kat*2, 1.0f, 0.0f, 0.0f);
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.16f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.16f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glEnd();
		
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch2	
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(0.0f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(0.0f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(0.0f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(0.0f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(0.0f, 0.0f, wheelBr);
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUAD_STRIP); //ko³o œrodek-bok
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.16f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.0f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.16f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.0f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelBr);
		gl.glEnd();
		gl.glPopMatrix();
		
		gl.glPushMatrix();//Ko³o prawe B4
		gl.glTranslatef(-0.42f, -0.62f, 0.52f);
		gl.glRotatef(kat*2, 1.0f, 0.0f, 0.0f);
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.16f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.16f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glEnd();
		
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch2	
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(0.0f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(0.0f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(0.0f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(0.0f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(0.0f, 0.0f, wheelBr);
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUAD_STRIP); //ko³o œrodek-bok
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.16f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.0f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.16f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.0f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelBr);
		gl.glEnd();
		gl.glPopMatrix();
		
		gl.glPushMatrix();//Ko³o prawe B5
		gl.glTranslatef(-0.58f, -0.62f, -0.45f);
		gl.glRotatef(kat*2, 1.0f, 0.0f, 0.0f);
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.08f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.08f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glEnd();
		
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch2	
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(0.0f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(0.0f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(0.0f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(0.0f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(0.0f, 0.0f, wheelBr);
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUAD_STRIP); //ko³o œrodek-bok
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.08f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.0f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.08f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.0f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelBr);
		gl.glEnd();
		gl.glPopMatrix();
		
		gl.glPushMatrix();//Ko³o prawe B6
		gl.glTranslatef(-0.58f, -0.62f, -0.06f);
		gl.glRotatef(kat*2, 1.0f, 0.0f, 0.0f);
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.08f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.08f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glEnd();
		
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch2	
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(0.0f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(0.0f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(0.0f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(0.0f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(0.0f, 0.0f, wheelBr);
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUAD_STRIP); //ko³o œrodek-bok
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.08f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.0f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.08f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.0f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelBr);
		gl.glEnd();
		gl.glPopMatrix();
		
		gl.glPushMatrix();//Ko³o prawe B7
		gl.glTranslatef(-0.58f, -0.62f, 0.33f);
		gl.glRotatef(kat*2, 1.0f, 0.0f, 0.0f);
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.08f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.08f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glEnd();
		
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch2	
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(0.0f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(0.0f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(0.0f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(0.0f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(0.0f, 0.0f, wheelBr);
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUAD_STRIP); //ko³o œrodek-bok
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.08f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.0f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.08f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.0f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelBr);
		gl.glEnd();
		gl.glPopMatrix();
		
		gl.glPushMatrix();//Ko³o prawe B8
		gl.glTranslatef(-0.58f, -0.62f, 0.72f);
		gl.glRotatef(kat*2, 1.0f, 0.0f, 0.0f);
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.08f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.08f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glEnd();
		
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch2	
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(0.0f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(0.0f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(0.0f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(0.0f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(0.0f, 0.0f, wheelBr);
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUAD_STRIP); //ko³o œrodek-bok
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.08f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.0f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.08f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.0f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelBr);
		gl.glEnd();
		gl.glPopMatrix();
		
		gl.glPushMatrix();// Ko³o prawe A
		gl.glTranslatef(-0.42f, -0.5f, 1.05f);
		gl.glRotatef(kat*2, 1.0f, 0.0f, 0.0f);
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch
		gl.glTexCoord2f(0.2549f,0.0327f); gl.glVertex3f(-0.2f, 0.0f, wheelAr);
		gl.glTexCoord2f(0.2500f,0.0488f); gl.glVertex3f(-0.2f, wheelAr*(float)Math.sin(Math.PI*30/180), wheelAr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.2383f,0.0605f); gl.glVertex3f(-0.2f, wheelAr*(float)Math.sin(Math.PI*60/180), wheelAr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.2222f,0.0645f); gl.glVertex3f(-0.2f, wheelAr, 0.0f);
		gl.glTexCoord2f(0.2061f,0.0601f); gl.glVertex3f(-0.2f, wheelAr*(float)Math.sin(Math.PI*120/180), wheelAr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1943f,0.0483f); gl.glVertex3f(-0.2f, wheelAr*(float)Math.sin(Math.PI*150/180), wheelAr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1899f,0.0327f); gl.glVertex3f(-0.2f, 0.0f, -wheelAr);
		gl.glTexCoord2f(0.1943f,0.0161f); gl.glVertex3f(-0.2f, -wheelAr*(float)Math.sin(Math.PI*150/180), wheelAr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.2061f,0.0044f); gl.glVertex3f(-0.2f, -wheelAr*(float)Math.sin(Math.PI*120/180), wheelAr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.2222f,0.0000f); gl.glVertex3f(-0.2f, -wheelAr, 0.0f);
		gl.glTexCoord2f(0.2383f,0.0044f); gl.glVertex3f(-0.2f, -wheelAr*(float)Math.sin(Math.PI*60/180), wheelAr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.2505f,0.0156f); gl.glVertex3f(-0.2f, -wheelAr*(float)Math.sin(Math.PI*30/180), wheelAr*(float)Math.cos(Math.PI*30/180));
		gl.glEnd();
		
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch2	
		gl.glTexCoord2f(0.2549f,0.0327f); gl.glVertex3f(0.0f, -wheelAr*(float)Math.sin(Math.PI*30/180), wheelAr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.2500f,0.0488f); gl.glVertex3f(0.0f, -wheelAr*(float)Math.sin(Math.PI*60/180), wheelAr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.2383f,0.0605f); gl.glVertex3f(0.0f, -wheelAr, 0.0f);
		gl.glTexCoord2f(0.2222f,0.0645f); gl.glVertex3f(0.0f, -wheelAr*(float)Math.sin(Math.PI*120/180), wheelAr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.2061f,0.0601f); gl.glVertex3f(0.0f, -wheelAr*(float)Math.sin(Math.PI*150/180), wheelAr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1943f,0.0483f); gl.glVertex3f(0.0f, 0.0f, -wheelAr);
		gl.glTexCoord2f(0.1899f,0.0327f); gl.glVertex3f(0.0f, wheelAr*(float)Math.sin(Math.PI*150/180), wheelAr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1943f,0.0161f); gl.glVertex3f(0.0f, wheelAr*(float)Math.sin(Math.PI*120/180), wheelAr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.2061f,0.0044f); gl.glVertex3f(0.0f, wheelAr, 0.0f);
		gl.glTexCoord2f(0.2222f,0.0000f); gl.glVertex3f(0.0f, wheelAr*(float)Math.sin(Math.PI*60/180), wheelAr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.2383f,0.0044f); gl.glVertex3f(0.0f, wheelAr*(float)Math.sin(Math.PI*30/180), wheelAr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.2505f,0.0156f); gl.glVertex3f(0.0f, 0.0f, wheelAr);
		gl.glTexCoord2f(0.2549f,0.0327f); gl.glVertex3f(0.0f, 0.0f, wheelAr);
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUAD_STRIP); //ko³o œrodek-bok
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.2f, 0.0f, wheelAr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelAr);
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.2f, wheelAr*(float)Math.sin(Math.PI*30/180), wheelAr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.0f, wheelAr*(float)Math.sin(Math.PI*30/180), wheelAr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.2f, wheelAr*(float)Math.sin(Math.PI*60/180), wheelAr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.0f, wheelAr*(float)Math.sin(Math.PI*60/180), wheelAr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.2f, wheelAr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.0f, wheelAr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.2f, wheelAr*(float)Math.sin(Math.PI*120/180), wheelAr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.0f, wheelAr*(float)Math.sin(Math.PI*120/180), wheelAr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.2f, wheelAr*(float)Math.sin(Math.PI*150/180), wheelAr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.0f, wheelAr*(float)Math.sin(Math.PI*150/180), wheelAr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.2f, 0.0f, -wheelAr);
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, -wheelAr);
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.2f, -wheelAr*(float)Math.sin(Math.PI*150/180), wheelAr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.0f, -wheelAr*(float)Math.sin(Math.PI*150/180), wheelAr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.2f, -wheelAr*(float)Math.sin(Math.PI*120/180), wheelAr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.0f, -wheelAr*(float)Math.sin(Math.PI*120/180), wheelAr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.2f, -wheelAr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.0f, -wheelAr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.2f, -wheelAr*(float)Math.sin(Math.PI*60/180), wheelAr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.0f, -wheelAr*(float)Math.sin(Math.PI*60/180), wheelAr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.2f, -wheelAr*(float)Math.sin(Math.PI*30/180), wheelAr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.0f, -wheelAr*(float)Math.sin(Math.PI*30/180), wheelAr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.2f, 0.0f, wheelAr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelAr);
		gl.glEnd();
		gl.glPopMatrix();
		
		gl.glPushMatrix();// Ko³o prawe C
		gl.glTranslatef(-0.42f, -0.59f, -1.0f);
		gl.glRotatef(kat*2, 1.0f, 0.0f, 0.0f);
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch
		gl.glTexCoord2f(0.1157f,0.0283f); gl.glVertex3f(-0.2f, 0.0f, wheelCr);
		gl.glTexCoord2f(0.1118f,0.0425f); gl.glVertex3f(-0.2f, wheelCr*(float)Math.sin(Math.PI*30/180), wheelCr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1016f,0.0522f); gl.glVertex3f(-0.2f, wheelCr*(float)Math.sin(Math.PI*60/180), wheelCr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.0879f,0.0557f); gl.glVertex3f(-0.2f, wheelCr, 0.0f);
		gl.glTexCoord2f(0.0737f,0.0522f); gl.glVertex3f(-0.2f, wheelCr*(float)Math.sin(Math.PI*120/180), wheelCr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.0640f,0.0425f); gl.glVertex3f(-0.2f, wheelCr*(float)Math.sin(Math.PI*150/180), wheelCr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.0605f,0.0283f); gl.glVertex3f(-0.2f, 0.0f, -wheelCr);
		gl.glTexCoord2f(0.0640f,0.0146f); gl.glVertex3f(-0.2f, -wheelCr*(float)Math.sin(Math.PI*150/180), wheelCr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.0737f,0.0039f); gl.glVertex3f(-0.2f, -wheelCr*(float)Math.sin(Math.PI*120/180), wheelCr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.0879f,0.0000f); gl.glVertex3f(-0.2f, -wheelCr, 0.0f);
		gl.glTexCoord2f(0.1016f,0.0039f); gl.glVertex3f(-0.2f, -wheelCr*(float)Math.sin(Math.PI*60/180), wheelCr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1118f,0.0146f); gl.glVertex3f(-0.2f, -wheelCr*(float)Math.sin(Math.PI*30/180), wheelCr*(float)Math.cos(Math.PI*30/180));
		gl.glEnd();
		
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch2	
		gl.glTexCoord2f(0.1157f,0.0283f); gl.glVertex3f(0.0f, -wheelCr*(float)Math.sin(Math.PI*30/180), wheelCr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1118f,0.0425f); gl.glVertex3f(0.0f, -wheelCr*(float)Math.sin(Math.PI*60/180), wheelCr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1016f,0.0522f); gl.glVertex3f(0.0f, -wheelCr, 0.0f);
		gl.glTexCoord2f(0.0879f,0.0557f); gl.glVertex3f(0.0f, -wheelCr*(float)Math.sin(Math.PI*120/180), wheelCr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.0737f,0.0522f); gl.glVertex3f(0.0f, -wheelCr*(float)Math.sin(Math.PI*150/180), wheelCr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.0640f,0.0425f); gl.glVertex3f(0.0f, 0.0f, -wheelCr);
		gl.glTexCoord2f(0.0605f,0.0283f); gl.glVertex3f(0.0f, wheelCr*(float)Math.sin(Math.PI*150/180), wheelCr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.0640f,0.0146f); gl.glVertex3f(0.0f, wheelCr*(float)Math.sin(Math.PI*120/180), wheelCr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.0737f,0.0039f); gl.glVertex3f(0.0f, wheelCr, 0.0f);
		gl.glTexCoord2f(0.0879f,0.0000f); gl.glVertex3f(0.0f, wheelCr*(float)Math.sin(Math.PI*60/180), wheelCr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1016f,0.0039f); gl.glVertex3f(0.0f, wheelCr*(float)Math.sin(Math.PI*30/180), wheelCr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1118f,0.0146f); gl.glVertex3f(0.0f, 0.0f, wheelCr);
		gl.glTexCoord2f(0.1157f,0.0283f); gl.glVertex3f(0.0f, 0.0f, wheelCr);
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUAD_STRIP); //ko³o œrodek-bok
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.2f, 0.0f, wheelCr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelCr);
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.2f, wheelCr*(float)Math.sin(Math.PI*30/180), wheelCr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.0f, wheelCr*(float)Math.sin(Math.PI*30/180), wheelCr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.2f, wheelCr*(float)Math.sin(Math.PI*60/180), wheelCr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.0f, wheelCr*(float)Math.sin(Math.PI*60/180), wheelCr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.2f, wheelCr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.0f, wheelCr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.2f, wheelCr*(float)Math.sin(Math.PI*120/180), wheelCr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.0f, wheelCr*(float)Math.sin(Math.PI*120/180), wheelCr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.2f, wheelCr*(float)Math.sin(Math.PI*150/180), wheelCr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.0f, wheelCr*(float)Math.sin(Math.PI*150/180), wheelCr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.2f, 0.0f, -wheelCr);
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, -wheelCr);
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.2f, -wheelCr*(float)Math.sin(Math.PI*150/180), wheelCr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.0f, -wheelCr*(float)Math.sin(Math.PI*150/180), wheelCr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.2f, -wheelCr*(float)Math.sin(Math.PI*120/180), wheelCr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.0f, -wheelCr*(float)Math.sin(Math.PI*120/180), wheelCr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.2f, -wheelCr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.0f, -wheelCr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.2f, -wheelCr*(float)Math.sin(Math.PI*60/180), wheelCr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.0f, -wheelCr*(float)Math.sin(Math.PI*60/180), wheelCr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.2f, -wheelCr*(float)Math.sin(Math.PI*30/180), wheelCr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.0f, -wheelCr*(float)Math.sin(Math.PI*30/180), wheelCr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.2f, 0.0f, wheelCr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelCr);
		gl.glEnd();
		gl.glPopMatrix();
		
		
		
		//////////////////////////////////////ko³a lewe ////////////////////////////////////////////////////
		gl.glPushMatrix();//Ko³o lewe B1
		gl.glTranslatef(0.58f, -0.62f, -0.65f);
		gl.glRotatef(kat*2, 1.0f, 0.0f, 0.0f);
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.16f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.16f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glEnd();
		
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch2	
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(0.0f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(0.0f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(0.0f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(0.0f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(0.0f, 0.0f, wheelBr);
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUAD_STRIP); //ko³o œrodek-bok
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.16f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.0f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.16f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.0f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelBr);
		gl.glEnd();
		gl.glPopMatrix();
		
		gl.glPushMatrix();//Ko³o lewe B2
		gl.glTranslatef(0.58f, -0.62f, -0.26f);
		gl.glRotatef(kat*2, 1.0f, 0.0f, 0.0f);
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.16f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.16f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glEnd();
		
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch2	
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(0.0f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(0.0f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(0.0f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(0.0f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(0.0f, 0.0f, wheelBr);
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUAD_STRIP); //ko³o œrodek-bok
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.16f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.0f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.16f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.0f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelBr);
		gl.glEnd();
		gl.glPopMatrix();
		
		gl.glPushMatrix();//Ko³o lewe B3
		gl.glTranslatef(0.58f, -0.62f, 0.13f);
		gl.glRotatef(kat*2, 1.0f, 0.0f, 0.0f);
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.16f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.16f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glEnd();
		
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch2	
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(0.0f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(0.0f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(0.0f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(0.0f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(0.0f, 0.0f, wheelBr);
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUAD_STRIP); //ko³o œrodek-bok
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.16f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.0f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.16f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.0f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelBr);
		gl.glEnd();
		gl.glPopMatrix();
		
		gl.glPushMatrix();//Ko³o lewe B4
		gl.glTranslatef(0.58f, -0.62f, 0.52f);
		gl.glRotatef(kat*2, 1.0f, 0.0f, 0.0f);
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.16f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.16f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glEnd();
		
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch2	
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(0.0f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(0.0f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(0.0f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(0.0f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(0.0f, 0.0f, wheelBr);
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUAD_STRIP); //ko³o œrodek-bok
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.16f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.0f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.16f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.16f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.0f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.16f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.16f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelBr);
		gl.glEnd();
		gl.glPopMatrix();
		
		gl.glPushMatrix();//Ko³o lewe B5
		gl.glTranslatef(0.66f, -0.62f, -0.45f);
		gl.glRotatef(kat*2, 1.0f, 0.0f, 0.0f);
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.08f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.08f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glEnd();
		
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch2	
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(0.0f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(0.0f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(0.0f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(0.0f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(0.0f, 0.0f, wheelBr);
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUAD_STRIP); //ko³o œrodek-bok
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.08f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.0f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.08f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.0f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelBr);
		gl.glEnd();
		gl.glPopMatrix();
		
		gl.glPushMatrix();//Ko³o lewe B6
		gl.glTranslatef(0.66f, -0.62f, -0.06f);
		gl.glRotatef(kat*2, 1.0f, 0.0f, 0.0f);
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.08f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.08f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glEnd();
		
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch2	
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(0.0f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(0.0f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(0.0f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(0.0f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(0.0f, 0.0f, wheelBr);
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUAD_STRIP); //ko³o œrodek-bok
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.08f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.0f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.08f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.0f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelBr);
		gl.glEnd();
		gl.glPopMatrix();
		
		gl.glPushMatrix();//Ko³o lewe B7
		gl.glTranslatef(0.66f, -0.62f, 0.33f);
		gl.glRotatef(kat*2, 1.0f, 0.0f, 0.0f);
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.08f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.08f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glEnd();
		
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch2	
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(0.0f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(0.0f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(0.0f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(0.0f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(0.0f, 0.0f, wheelBr);
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUAD_STRIP); //ko³o œrodek-bok
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.08f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.0f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.08f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.0f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelBr);
		gl.glEnd();
		gl.glPopMatrix();
		
		gl.glPushMatrix();//Ko³o lewe B8
		gl.glTranslatef(0.66f, -0.62f, 0.72f);
		gl.glRotatef(kat*2, 1.0f, 0.0f, 0.0f);
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.08f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.08f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glEnd();
		
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch2	
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(0.0f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(0.0f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(0.0f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(0.0f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(0.0f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(0.0f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(0.0f, 0.0f, wheelBr);
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUAD_STRIP); //ko³o œrodek-bok
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.08f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.0f, wheelBr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.08f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.0f, wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, -wheelBr);
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*150/180), wheelBr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*120/180), wheelBr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.08f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.0f, -wheelBr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*60/180), wheelBr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.08f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.0f, -wheelBr*(float)Math.sin(Math.PI*30/180), wheelBr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.08f, 0.0f, wheelBr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelBr);
		gl.glEnd();
		gl.glPopMatrix();
		
		gl.glPushMatrix();// Ko³o lewe A
		gl.glTranslatef(0.62f, -0.5f, 1.05f);
		gl.glRotatef(kat*2, 1.0f, 0.0f, 0.0f);
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch
		gl.glTexCoord2f(0.2549f,0.0327f); gl.glVertex3f(-0.2f, 0.0f, wheelAr);
		gl.glTexCoord2f(0.2500f,0.0488f); gl.glVertex3f(-0.2f, wheelAr*(float)Math.sin(Math.PI*30/180), wheelAr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.2383f,0.0605f); gl.glVertex3f(-0.2f, wheelAr*(float)Math.sin(Math.PI*60/180), wheelAr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.2222f,0.0645f); gl.glVertex3f(-0.2f, wheelAr, 0.0f);
		gl.glTexCoord2f(0.2061f,0.0601f); gl.glVertex3f(-0.2f, wheelAr*(float)Math.sin(Math.PI*120/180), wheelAr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1943f,0.0483f); gl.glVertex3f(-0.2f, wheelAr*(float)Math.sin(Math.PI*150/180), wheelAr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1899f,0.0327f); gl.glVertex3f(-0.2f, 0.0f, -wheelAr);
		gl.glTexCoord2f(0.1943f,0.0161f); gl.glVertex3f(-0.2f, -wheelAr*(float)Math.sin(Math.PI*150/180), wheelAr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.2061f,0.0044f); gl.glVertex3f(-0.2f, -wheelAr*(float)Math.sin(Math.PI*120/180), wheelAr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.2222f,0.0000f); gl.glVertex3f(-0.2f, -wheelAr, 0.0f);
		gl.glTexCoord2f(0.2383f,0.0044f); gl.glVertex3f(-0.2f, -wheelAr*(float)Math.sin(Math.PI*60/180), wheelAr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.2505f,0.0156f); gl.glVertex3f(-0.2f, -wheelAr*(float)Math.sin(Math.PI*30/180), wheelAr*(float)Math.cos(Math.PI*30/180));
		gl.glEnd();
		
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch2	
		gl.glTexCoord2f(0.2549f,0.0327f); gl.glVertex3f(0.0f, -wheelAr*(float)Math.sin(Math.PI*30/180), wheelAr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.2500f,0.0488f); gl.glVertex3f(0.0f, -wheelAr*(float)Math.sin(Math.PI*60/180), wheelAr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.2383f,0.0605f); gl.glVertex3f(0.0f, -wheelAr, 0.0f);
		gl.glTexCoord2f(0.2222f,0.0645f); gl.glVertex3f(0.0f, -wheelAr*(float)Math.sin(Math.PI*120/180), wheelAr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.2061f,0.0601f); gl.glVertex3f(0.0f, -wheelAr*(float)Math.sin(Math.PI*150/180), wheelAr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1943f,0.0483f); gl.glVertex3f(0.0f, 0.0f, -wheelAr);
		gl.glTexCoord2f(0.1899f,0.0327f); gl.glVertex3f(0.0f, wheelAr*(float)Math.sin(Math.PI*150/180), wheelAr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1943f,0.0161f); gl.glVertex3f(0.0f, wheelAr*(float)Math.sin(Math.PI*120/180), wheelAr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.2061f,0.0044f); gl.glVertex3f(0.0f, wheelAr, 0.0f);
		gl.glTexCoord2f(0.2222f,0.0000f); gl.glVertex3f(0.0f, wheelAr*(float)Math.sin(Math.PI*60/180), wheelAr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.2383f,0.0044f); gl.glVertex3f(0.0f, wheelAr*(float)Math.sin(Math.PI*30/180), wheelAr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.2505f,0.0156f); gl.glVertex3f(0.0f, 0.0f, wheelAr);
		gl.glTexCoord2f(0.2549f,0.0327f); gl.glVertex3f(0.0f, 0.0f, wheelAr);
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUAD_STRIP); //ko³o œrodek-bok
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.2f, 0.0f, wheelAr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelAr);
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.2f, wheelAr*(float)Math.sin(Math.PI*30/180), wheelAr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.0f, wheelAr*(float)Math.sin(Math.PI*30/180), wheelAr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.2f, wheelAr*(float)Math.sin(Math.PI*60/180), wheelAr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.0f, wheelAr*(float)Math.sin(Math.PI*60/180), wheelAr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.2f, wheelAr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.0f, wheelAr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.2f, wheelAr*(float)Math.sin(Math.PI*120/180), wheelAr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.0f, wheelAr*(float)Math.sin(Math.PI*120/180), wheelAr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.2f, wheelAr*(float)Math.sin(Math.PI*150/180), wheelAr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.0f, wheelAr*(float)Math.sin(Math.PI*150/180), wheelAr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.2f, 0.0f, -wheelAr);
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, -wheelAr);
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.2f, -wheelAr*(float)Math.sin(Math.PI*150/180), wheelAr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.0f, -wheelAr*(float)Math.sin(Math.PI*150/180), wheelAr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.2f, -wheelAr*(float)Math.sin(Math.PI*120/180), wheelAr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.0f, -wheelAr*(float)Math.sin(Math.PI*120/180), wheelAr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.2f, -wheelAr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.0f, -wheelAr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.2f, -wheelAr*(float)Math.sin(Math.PI*60/180), wheelAr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.0f, -wheelAr*(float)Math.sin(Math.PI*60/180), wheelAr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.2f, -wheelAr*(float)Math.sin(Math.PI*30/180), wheelAr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.0f, -wheelAr*(float)Math.sin(Math.PI*30/180), wheelAr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.2f, 0.0f, wheelAr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelAr);
		gl.glEnd();
		gl.glPopMatrix();
		
		gl.glPushMatrix();// Ko³o lewe C
		gl.glTranslatef(0.62f, -0.59f, -1.0f);
		gl.glRotatef(kat*2, 1.0f, 0.0f, 0.0f);
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch
		gl.glTexCoord2f(0.1157f,0.0283f); gl.glVertex3f(-0.2f, 0.0f, wheelCr);
		gl.glTexCoord2f(0.1118f,0.0425f); gl.glVertex3f(-0.2f, wheelCr*(float)Math.sin(Math.PI*30/180), wheelCr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1016f,0.0522f); gl.glVertex3f(-0.2f, wheelCr*(float)Math.sin(Math.PI*60/180), wheelCr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.0879f,0.0557f); gl.glVertex3f(-0.2f, wheelCr, 0.0f);
		gl.glTexCoord2f(0.0737f,0.0522f); gl.glVertex3f(-0.2f, wheelCr*(float)Math.sin(Math.PI*120/180), wheelCr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.0640f,0.0425f); gl.glVertex3f(-0.2f, wheelCr*(float)Math.sin(Math.PI*150/180), wheelCr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.0605f,0.0283f); gl.glVertex3f(-0.2f, 0.0f, -wheelCr);
		gl.glTexCoord2f(0.0640f,0.0146f); gl.glVertex3f(-0.2f, -wheelCr*(float)Math.sin(Math.PI*150/180), wheelCr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.0737f,0.0039f); gl.glVertex3f(-0.2f, -wheelCr*(float)Math.sin(Math.PI*120/180), wheelCr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.0879f,0.0000f); gl.glVertex3f(-0.2f, -wheelCr, 0.0f);
		gl.glTexCoord2f(0.1016f,0.0039f); gl.glVertex3f(-0.2f, -wheelCr*(float)Math.sin(Math.PI*60/180), wheelCr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1118f,0.0146f); gl.glVertex3f(-0.2f, -wheelCr*(float)Math.sin(Math.PI*30/180), wheelCr*(float)Math.cos(Math.PI*30/180));
		gl.glEnd();
		
		gl.glBegin(GL2.GL_POLYGON); //ko³o œrodek-wierzch2	
		gl.glTexCoord2f(0.1157f,0.0283f); gl.glVertex3f(0.0f, -wheelCr*(float)Math.sin(Math.PI*30/180), wheelCr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1118f,0.0425f); gl.glVertex3f(0.0f, -wheelCr*(float)Math.sin(Math.PI*60/180), wheelCr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1016f,0.0522f); gl.glVertex3f(0.0f, -wheelCr, 0.0f);
		gl.glTexCoord2f(0.0879f,0.0557f); gl.glVertex3f(0.0f, -wheelCr*(float)Math.sin(Math.PI*120/180), wheelCr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.0737f,0.0522f); gl.glVertex3f(0.0f, -wheelCr*(float)Math.sin(Math.PI*150/180), wheelCr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.0640f,0.0425f); gl.glVertex3f(0.0f, 0.0f, -wheelCr);
		gl.glTexCoord2f(0.0605f,0.0283f); gl.glVertex3f(0.0f, wheelCr*(float)Math.sin(Math.PI*150/180), wheelCr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.0640f,0.0146f); gl.glVertex3f(0.0f, wheelCr*(float)Math.sin(Math.PI*120/180), wheelCr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.0737f,0.0039f); gl.glVertex3f(0.0f, wheelCr, 0.0f);
		gl.glTexCoord2f(0.0879f,0.0000f); gl.glVertex3f(0.0f, wheelCr*(float)Math.sin(Math.PI*60/180), wheelCr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1016f,0.0039f); gl.glVertex3f(0.0f, wheelCr*(float)Math.sin(Math.PI*30/180), wheelCr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1118f,0.0146f); gl.glVertex3f(0.0f, 0.0f, wheelCr);
		gl.glTexCoord2f(0.1157f,0.0283f); gl.glVertex3f(0.0f, 0.0f, wheelCr);
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUAD_STRIP); //ko³o œrodek-bok
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.2f, 0.0f, wheelCr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelCr);
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.2f, wheelCr*(float)Math.sin(Math.PI*30/180), wheelCr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1821f,0.0518f); gl.glVertex3f(-0.0f, wheelCr*(float)Math.sin(Math.PI*30/180), wheelCr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.2f, wheelCr*(float)Math.sin(Math.PI*60/180), wheelCr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0640f); gl.glVertex3f(-0.0f, wheelCr*(float)Math.sin(Math.PI*60/180), wheelCr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.2f, wheelCr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0684f); gl.glVertex3f(-0.0f, wheelCr, 0.0f);
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.2f, wheelCr*(float)Math.sin(Math.PI*120/180), wheelCr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0635f); gl.glVertex3f(-0.0f, wheelCr*(float)Math.sin(Math.PI*120/180), wheelCr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.2f, wheelCr*(float)Math.sin(Math.PI*150/180), wheelCr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0508f); gl.glVertex3f(-0.0f, wheelCr*(float)Math.sin(Math.PI*150/180), wheelCr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.2f, 0.0f, -wheelCr);
		gl.glTexCoord2f(0.1196f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, -wheelCr);
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.2f, -wheelCr*(float)Math.sin(Math.PI*150/180), wheelCr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1235f,0.0176f); gl.glVertex3f(-0.0f, -wheelCr*(float)Math.sin(Math.PI*150/180), wheelCr*(float)Math.cos(Math.PI*150/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.2f, -wheelCr*(float)Math.sin(Math.PI*120/180), wheelCr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1357f,0.0049f); gl.glVertex3f(-0.0f, -wheelCr*(float)Math.sin(Math.PI*120/180), wheelCr*(float)Math.cos(Math.PI*120/180));
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.2f, -wheelCr, 0.0f);
		gl.glTexCoord2f(0.1528f,0.0000f); gl.glVertex3f(-0.0f, -wheelCr, 0.0f);
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.2f, -wheelCr*(float)Math.sin(Math.PI*60/180), wheelCr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1699f,0.0049f); gl.glVertex3f(-0.0f, -wheelCr*(float)Math.sin(Math.PI*60/180), wheelCr*(float)Math.cos(Math.PI*60/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.2f, -wheelCr*(float)Math.sin(Math.PI*30/180), wheelCr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1826f,0.0171f); gl.glVertex3f(-0.0f, -wheelCr*(float)Math.sin(Math.PI*30/180), wheelCr*(float)Math.cos(Math.PI*30/180));
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.2f, 0.0f, wheelCr);
		gl.glTexCoord2f(0.1870f,0.0347f); gl.glVertex3f(-0.0f, 0.0f, wheelCr);
		gl.glEnd();
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glPushMatrix();
		gl.glBindTexture(GL2.GL_TEXTURE_2D, texture2);
		gl.glTexParameterf(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT);
		gl.glTexParameterf(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_T, GL2.GL_REPEAT);
		gl.glMatrixMode(GL2.GL_TEXTURE);
		gl.glLoadIdentity();
		gl.glTranslatef(kat*5.0f/360, 0.0f, 0.0f);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glTranslatef(-0.4f, -0.2f, -1.1f);
		//gl.glRotatef(kat, 0.0f, 1.0f, 0.0f);
		gl.glBegin(GL2.GL_QUAD_STRIP); //dym prawy
		gl.glTexCoord2f(1.0f,0.0f); gl.glVertex3f(-0.15f, 0.0f, 0.0f);
		gl.glTexCoord2f(1.0f,1.0f); gl.glVertex3f(0.15f, 0.0f, 0.0f);
		gl.glTexCoord2f(0.0f,0.0f); gl.glVertex3f(-0.15f, 1.0f, 0.0f);
		gl.glTexCoord2f(0.0f,1.0f); gl.glVertex3f(0.15f, 1.0f, 0.0f);
		gl.glTexCoord2f(1.0f,0.0f); gl.glVertex3f(-0.15f, 0.0f, 0.0f);
		gl.glTexCoord2f(1.0f,1.0f); gl.glVertex3f(0.15f, 0.0f, 0.0f);
		gl.glEnd();
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glBindTexture(GL2.GL_TEXTURE_2D, texture2);
		gl.glTexParameterf(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT);
		gl.glTexParameterf(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_T, GL2.GL_REPEAT);
		gl.glMatrixMode(GL2.GL_TEXTURE);
		gl.glLoadIdentity();
		gl.glTranslatef(kat*5.0f/360, 0.0f, 0.0f);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glTranslatef(0.4f, -0.2f, -1.1f);
		//gl.glRotatef(kat, 0.0f, 1.0f, 0.0f);
		gl.glBegin(GL2.GL_QUAD_STRIP); //dym prawy
		gl.glTexCoord2f(1.0f,0.0f); gl.glVertex3f(-0.15f, 0.0f, 0.0f);
		gl.glTexCoord2f(1.0f,1.0f); gl.glVertex3f(0.15f, 0.0f, 0.0f);
		gl.glTexCoord2f(0.0f,0.0f); gl.glVertex3f(-0.15f, 1.0f, 0.0f);
		gl.glTexCoord2f(0.0f,1.0f); gl.glVertex3f(0.15f, 1.0f, 0.0f);
		gl.glTexCoord2f(1.0f,0.0f); gl.glVertex3f(-0.15f, 0.0f, 0.0f);
		gl.glTexCoord2f(1.0f,1.0f); gl.glVertex3f(0.15f, 0.0f, 0.0f);
		gl.glEnd();
		gl.glPopMatrix();
		gl.glPopMatrix();
		
		
		gl.glPushMatrix();
		gl.glBindTexture(GL2.GL_TEXTURE_2D, texture1);
		gl.glTexParameterf(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT);
		gl.glTexParameterf(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_T, GL2.GL_REPEAT);
		gl.glMatrixMode(GL2.GL_TEXTURE);
		gl.glLoadIdentity();
		gl.glTranslatef(-kat*5.0f/360, 0.0f, 0.0f);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		
		///////////////////////////////////////// g¹siennica lewa //////////////////////////////////////////
		gl.glBegin(GL2.GL_QUADS);//wierzch
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(0.75f, -0.42f, -1.05f);
		gl.glTexCoord2f(0.0f,1.0f); gl.glVertex3f(0.42f, -0.42f, -1.05f);
		gl.glTexCoord2f(5.0f,1.0f); gl.glVertex3f(0.42f, -0.42f, 0.6f);
		gl.glTexCoord2f(5.0f,0.5f); gl.glVertex3f(0.75f, -0.42f, 0.6f);
		
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(0.75f, -0.42f, 0.6f);
		gl.glTexCoord2f(0.0f,1.0f); gl.glVertex3f(0.42f, -0.42f, 0.6f);
		gl.glTexCoord2f(1.5f,1.0f); gl.glVertex3f(0.42f, -0.29f, 1.05f);
		gl.glTexCoord2f(1.5f,0.5f); gl.glVertex3f(0.75f, -0.29f, 1.05f);
		
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(0.75f, -0.29f, 1.05f);
		gl.glTexCoord2f(0.0f,1.0f); gl.glVertex3f(0.42f, -0.29f, 1.05f);
		gl.glTexCoord2f(0.25f,1.0f); gl.glVertex3f(0.42f, -0.29f, 1.13f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(0.75f, -0.29f, 1.13f);
		
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(0.75f, -0.29f, 1.13f);
		gl.glTexCoord2f(0.0f,1.0f); gl.glVertex3f(0.42f, -0.29f, 1.13f);
		gl.glTexCoord2f(0.25f,1.0f); gl.glVertex3f(0.42f, -0.35f, 1.2f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(0.75f, -0.35f, 1.2f);
		
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(0.75f,-0.35f, 1.2f);
		gl.glTexCoord2f(0.0f,1.0f); gl.glVertex3f(0.42f,-0.35f, 1.2f);
		gl.glTexCoord2f(0.25f,1.0f); gl.glVertex3f(0.42f, -0.44f, 1.25f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(0.75f, -0.44f, 1.25f);
		
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(0.75f, -0.44f, 1.25f);
		gl.glTexCoord2f(0.0f,1.0f); gl.glVertex3f(0.42f, -0.44f, 1.25f);
		gl.glTexCoord2f(0.25f,1.0f); gl.glVertex3f(0.42f, -0.55f, 1.25f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(0.75f, -0.55f, 1.25f);
		
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(0.75f, -0.55f, 1.25f);
		gl.glTexCoord2f(0.0f,1.0f); gl.glVertex3f(0.42f, -0.55f, 1.25f);
		gl.glTexCoord2f(0.25f,1.0f); gl.glVertex3f(0.42f, -0.63f, 1.21f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(0.75f, -0.63f, 1.21f);
		
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(0.75f, -0.63f, 1.21f);
		gl.glTexCoord2f(0.0f,1.0f); gl.glVertex3f(0.42f, -0.63f, 1.21f);
		gl.glTexCoord2f(0.25f,1.0f); gl.glVertex3f(0.42f, -0.68f, 1.15f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(0.75f, -0.68f, 1.15f);
		
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(0.75f, -0.68f, 1.15f);
		gl.glTexCoord2f(0.0f,1.0f); gl.glVertex3f(0.42f, -0.68f, 1.15f);
		gl.glTexCoord2f(1.25f,1.0f); gl.glVertex3f(0.42f, -0.8f, 0.79f);
		gl.glTexCoord2f(1.25f,0.5f); gl.glVertex3f(0.75f, -0.8f, 0.79f);
		
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(0.75f, -0.8f, 0.79f);
		gl.glTexCoord2f(0.0f,1.0f); gl.glVertex3f(0.42f, -0.8f, 0.79f);
		gl.glTexCoord2f(4f,1.0f); gl.glVertex3f(0.42f, -0.8f, -0.70f);
		gl.glTexCoord2f(4f,0.5f); gl.glVertex3f(0.75f, -0.8f, -0.70f);
		
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(0.75f, -0.8f, -0.70f);
		gl.glTexCoord2f(0.0f,1.0f); gl.glVertex3f(0.42f, -0.8f, -0.70f);
		gl.glTexCoord2f(1.0f,1.0f); gl.glVertex3f(0.42f, -0.75f, -1.03f);
		gl.glTexCoord2f(1.0f,0.5f); gl.glVertex3f(0.75f, -0.75f, -1.03f);
		
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(0.75f, -0.75f, -1.03f);
		gl.glTexCoord2f(0.0f,1.0f); gl.glVertex3f(0.42f, -0.75f, -1.03f);
		gl.glTexCoord2f(0.25f,1.0f); gl.glVertex3f(0.42f, -0.72f, -1.11f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(0.75f, -0.72f, -1.11f);
		
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(0.75f, -0.72f, -1.11f);
		gl.glTexCoord2f(0.0f,1.0f); gl.glVertex3f(0.42f, -0.72f, -1.11f);
		gl.glTexCoord2f(0.25f,1.0f); gl.glVertex3f(0.42f, -0.65f, -1.16f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(0.75f, -0.65f, -1.16f);
		
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(0.75f, -0.65f, -1.16f);
		gl.glTexCoord2f(0.0f,1.0f); gl.glVertex3f(0.42f, -0.65f, -1.16f);
		gl.glTexCoord2f(0.25f,1.0f); gl.glVertex3f(0.42f, -0.55f, -1.16f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(0.75f, -0.55f, -1.16f);
		
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(0.75f, -0.55f, -1.16f);
		gl.glTexCoord2f(0.0f,1.0f); gl.glVertex3f(0.42f, -0.55f, -1.16f);
		gl.glTexCoord2f(0.25f,1.0f); gl.glVertex3f(0.42f, -0.45f, -1.11f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(0.75f, -0.45f, -1.11f);
		
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(0.75f, -0.45f, -1.11f);
		gl.glTexCoord2f(0.0f,1.0f); gl.glVertex3f(0.42f, -0.45f, -1.11f);
		gl.glTexCoord2f(0.25f,1.0f); gl.glVertex3f(0.42f, -0.42f, -1.05f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(0.75f, -0.42f, -1.05f);
		
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUADS);//wnêtrze
		gl.glTexCoord2f(0.0f,0.0f); gl.glVertex3f(0.42f, -0.42f, -1.05f);
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(0.75f, -0.42f, -1.05f);
		gl.glTexCoord2f(5.0f,0.5f); gl.glVertex3f(0.75f, -0.42f, 0.6f);
		gl.glTexCoord2f(5.0f,0.0f); gl.glVertex3f(0.42f, -0.42f, 0.6f);
		
		gl.glTexCoord2f(0.0f,0.0f); gl.glVertex3f(0.42f, -0.42f, 0.6f);
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(0.75f, -0.42f, 0.6f);
		gl.glTexCoord2f(1.5f,0.5f); gl.glVertex3f(0.75f, -0.29f, 1.05f);
		gl.glTexCoord2f(1.5f,0.0f); gl.glVertex3f(0.42f, -0.29f, 1.05f);
		
		gl.glTexCoord2f(0.0f,0.0f); gl.glVertex3f(0.42f, -0.29f, 1.05f);
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(0.75f, -0.29f, 1.05f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(0.75f, -0.29f, 1.13f);
		gl.glTexCoord2f(0.25f,0.0f); gl.glVertex3f(0.42f, -0.29f, 1.13f);
		
		gl.glTexCoord2f(0.0f,0.0f); gl.glVertex3f(0.42f, -0.29f, 1.13f);
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(0.75f, -0.29f, 1.13f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(0.75f, -0.35f, 1.2f);
		gl.glTexCoord2f(0.25f,0.0f); gl.glVertex3f(0.42f, -0.35f, 1.2f);
		
		gl.glTexCoord2f(0.0f,0.0f); gl.glVertex3f(0.42f,-0.35f, 1.2f);
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(0.75f,-0.35f, 1.2f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(0.75f, -0.44f, 1.25f);
		gl.glTexCoord2f(0.25f,0.0f); gl.glVertex3f(0.42f, -0.44f, 1.25f);
		
		gl.glTexCoord2f(0.0f,0.0f); gl.glVertex3f(0.42f, -0.44f, 1.25f);
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(0.75f, -0.44f, 1.25f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(0.75f, -0.55f, 1.25f);
		gl.glTexCoord2f(0.25f,0.0f); gl.glVertex3f(0.42f, -0.55f, 1.25f);
		
		gl.glTexCoord2f(0.0f,0.0f); gl.glVertex3f(0.42f, -0.55f, 1.25f);
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(0.75f, -0.55f, 1.25f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(0.75f, -0.63f, 1.21f);
		gl.glTexCoord2f(0.25f,0.0f); gl.glVertex3f(0.42f, -0.63f, 1.21f);
		
		gl.glTexCoord2f(0.0f,0.0f); gl.glVertex3f(0.42f, -0.63f, 1.21f);
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(0.75f, -0.63f, 1.21f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(0.75f, -0.68f, 1.15f);
		gl.glTexCoord2f(0.25f,0.0f); gl.glVertex3f(0.42f, -0.68f, 1.15f);
		
		gl.glTexCoord2f(0.0f,0.0f); gl.glVertex3f(0.42f, -0.68f, 1.15f);
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(0.75f, -0.68f, 1.15f);
		gl.glTexCoord2f(1.25f,0.5f); gl.glVertex3f(0.75f, -0.8f, 0.79f);
		gl.glTexCoord2f(1.25f,0.0f); gl.glVertex3f(0.42f, -0.8f, 0.79f);
		
		gl.glTexCoord2f(0.0f,0.0f); gl.glVertex3f(0.42f, -0.8f, 0.79f);
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(0.75f, -0.8f, 0.79f);
		gl.glTexCoord2f(4f,0.5f); gl.glVertex3f(0.75f, -0.8f, -0.70f);
		gl.glTexCoord2f(4f,0.0f); gl.glVertex3f(0.42f, -0.8f, -0.70f);
		
		gl.glTexCoord2f(0.0f,0.0f); gl.glVertex3f(0.42f, -0.8f, -0.70f);
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(0.75f, -0.8f, -0.70f);
		gl.glTexCoord2f(1.0f,0.5f); gl.glVertex3f(0.75f, -0.75f, -1.03f);
		gl.glTexCoord2f(1.0f,0.0f); gl.glVertex3f(0.42f, -0.75f, -1.03f);
		
		gl.glTexCoord2f(0.0f,0.0f); gl.glVertex3f(0.42f, -0.75f, -1.03f);
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(0.75f, -0.75f, -1.03f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(0.75f, -0.72f, -1.11f);
		gl.glTexCoord2f(0.25f,0.0f); gl.glVertex3f(0.42f, -0.72f, -1.11f);
		
		gl.glTexCoord2f(0.0f,0.0f); gl.glVertex3f(0.42f, -0.72f, -1.11f);
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(0.75f, -0.72f, -1.11f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(0.75f, -0.65f, -1.16f);
		gl.glTexCoord2f(0.25f,0.0f); gl.glVertex3f(0.42f, -0.65f, -1.16f);
		
		gl.glTexCoord2f(0.0f,0.0f); gl.glVertex3f(0.42f, -0.65f, -1.16f);
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(0.75f, -0.65f, -1.16f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(0.75f, -0.55f, -1.16f);
		gl.glTexCoord2f(0.25f,0.0f); gl.glVertex3f(0.42f, -0.55f, -1.16f);
		
		gl.glTexCoord2f(0.0f,0.0f); gl.glVertex3f(0.42f, -0.55f, -1.16f);
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(0.75f, -0.55f, -1.16f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(0.75f, -0.45f, -1.11f);
		gl.glTexCoord2f(0.25f,0.0f); gl.glVertex3f(0.42f, -0.45f, -1.11f);
		
		gl.glTexCoord2f(0.0f,0.0f); gl.glVertex3f(0.42f, -0.45f, -1.11f);
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(0.75f, -0.45f, -1.11f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(0.75f, -0.42f, -1.05f);
		gl.glTexCoord2f(0.25f,0.0f); gl.glVertex3f(0.42f, -0.42f, -1.05f);
		
		gl.glEnd();
		
		///////////////////////////////////////// g¹siennica prawa ///////////////////////////////////////////
		gl.glBegin(GL2.GL_QUADS);//wierzch
		gl.glTexCoord2f(0.0f,1.0f); gl.glVertex3f(-0.42f, -0.42f, -1.05f);
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(-0.75f, -0.42f, -1.05f);
		gl.glTexCoord2f(5.0f,0.5f); gl.glVertex3f(-0.75f, -0.42f, 0.6f);
		gl.glTexCoord2f(5.0f,1.0f); gl.glVertex3f(-0.42f, -0.42f, 0.6f);
		
		gl.glTexCoord2f(0.0f,1.0f); gl.glVertex3f(-0.42f, -0.42f, 0.6f);
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(-0.75f, -0.42f, 0.6f);
		gl.glTexCoord2f(1.5f,0.5f); gl.glVertex3f(-0.75f, -0.29f, 1.05f);
		gl.glTexCoord2f(1.5f,1.0f); gl.glVertex3f(-0.42f, -0.29f, 1.05f);
		
		gl.glTexCoord2f(0.0f,1.0f); gl.glVertex3f(-0.42f, -0.29f, 1.05f);
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(-0.75f, -0.29f, 1.05f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(-0.75f, -0.29f, 1.13f);
		gl.glTexCoord2f(0.25f,1.0f); gl.glVertex3f(-0.42f, -0.29f, 1.13f);
		
		gl.glTexCoord2f(0.0f,1.0f); gl.glVertex3f(-0.42f, -0.29f, 1.13f);
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(-0.75f, -0.29f, 1.13f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(-0.75f, -0.35f, 1.2f);
		gl.glTexCoord2f(0.25f,1.0f); gl.glVertex3f(-0.42f, -0.35f, 1.2f);
		
		gl.glTexCoord2f(0.0f,1.0f); gl.glVertex3f(-0.42f,-0.35f, 1.2f);
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(-0.75f,-0.35f, 1.2f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(-0.75f, -0.44f, 1.25f);
		gl.glTexCoord2f(0.25f,1.0f); gl.glVertex3f(-0.42f, -0.44f, 1.25f);
		
		gl.glTexCoord2f(0.0f,1.0f); gl.glVertex3f(-0.42f, -0.44f, 1.25f);
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(-0.75f, -0.44f, 1.25f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(-0.75f, -0.55f, 1.25f);
		gl.glTexCoord2f(0.25f,1.0f); gl.glVertex3f(-0.42f, -0.55f, 1.25f);
		
		gl.glTexCoord2f(0.0f,1.0f); gl.glVertex3f(-0.42f, -0.55f, 1.25f);
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(-0.75f, -0.55f, 1.25f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(-0.75f, -0.63f, 1.21f);
		gl.glTexCoord2f(0.25f,1.0f); gl.glVertex3f(-0.42f, -0.63f, 1.21f);
		
		gl.glTexCoord2f(0.0f,1.0f); gl.glVertex3f(-0.42f, -0.63f, 1.21f);
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(-0.75f, -0.63f, 1.21f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(-0.75f, -0.68f, 1.15f);
		gl.glTexCoord2f(0.25f,1.0f); gl.glVertex3f(-0.42f, -0.68f, 1.15f);
		
		gl.glTexCoord2f(0.0f,1.0f); gl.glVertex3f(-0.42f, -0.68f, 1.15f);
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(-0.75f, -0.68f, 1.15f);
		gl.glTexCoord2f(1.25f,0.5f); gl.glVertex3f(-0.75f, -0.8f, 0.79f);
		gl.glTexCoord2f(1.25f,1.0f); gl.glVertex3f(-0.42f, -0.8f, 0.79f);
		
		gl.glTexCoord2f(0.0f,1.0f); gl.glVertex3f(-0.42f, -0.8f, 0.79f);
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(-0.75f, -0.8f, 0.79f);
		gl.glTexCoord2f(4f,0.5f); gl.glVertex3f(-0.75f, -0.8f, -0.70f);
		gl.glTexCoord2f(4f,1.0f); gl.glVertex3f(-0.42f, -0.8f, -0.70f);
		
		gl.glTexCoord2f(0.0f,1.0f); gl.glVertex3f(-0.42f, -0.8f, -0.70f);
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(-0.75f, -0.8f, -0.70f);
		gl.glTexCoord2f(1.0f,0.5f); gl.glVertex3f(-0.75f, -0.75f, -1.03f);
		gl.glTexCoord2f(1.0f,1.0f); gl.glVertex3f(-0.42f, -0.75f, -1.03f);
		
		gl.glTexCoord2f(0.0f,1.0f); gl.glVertex3f(-0.42f, -0.75f, -1.03f);
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(-0.75f, -0.75f, -1.03f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(-0.75f, -0.72f, -1.11f);
		gl.glTexCoord2f(0.25f,1.0f); gl.glVertex3f(-0.42f, -0.72f, -1.11f);
		
		gl.glTexCoord2f(0.0f,1.0f); gl.glVertex3f(-0.42f, -0.72f, -1.11f);
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(-0.75f, -0.72f, -1.11f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(-0.75f, -0.65f, -1.16f);
		gl.glTexCoord2f(0.25f,1.0f); gl.glVertex3f(-0.42f, -0.65f, -1.16f);

		gl.glTexCoord2f(0.0f,1.0f); gl.glVertex3f(-0.42f, -0.65f, -1.16f);
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(-0.75f, -0.65f, -1.16f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(-0.75f, -0.55f, -1.16f);
		gl.glTexCoord2f(0.25f,1.0f); gl.glVertex3f(-0.42f, -0.55f, -1.16f);

		gl.glTexCoord2f(0.0f,1.0f); gl.glVertex3f(-0.42f, -0.55f, -1.16f);
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(-0.75f, -0.55f, -1.16f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(-0.75f, -0.45f, -1.11f);
		gl.glTexCoord2f(0.25f,1.0f); gl.glVertex3f(-0.42f, -0.45f, -1.11f);

		gl.glTexCoord2f(0.0f,1.0f); gl.glVertex3f(-0.42f, -0.45f, -1.11f);
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(-0.75f, -0.45f, -1.11f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(-0.75f, -0.42f, -1.05f);
		gl.glTexCoord2f(0.25f,1.0f); gl.glVertex3f(-0.42f, -0.42f, -1.05f);
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUADS);//wnêtrze
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(-0.75f, -0.42f, -1.05f);
		gl.glTexCoord2f(0.0f,0.0f); gl.glVertex3f(-0.42f, -0.42f, -1.05f);
		gl.glTexCoord2f(5.0f,0.0f); gl.glVertex3f(-0.42f, -0.42f, 0.6f);
		gl.glTexCoord2f(5.0f,0.5f); gl.glVertex3f(-0.75f, -0.42f, 0.6f);
		
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(-0.75f, -0.42f, 0.6f);
		gl.glTexCoord2f(0.0f,0.0f); gl.glVertex3f(-0.42f, -0.42f, 0.6f);
		gl.glTexCoord2f(1.5f,0.0f); gl.glVertex3f(-0.42f, -0.29f, 1.05f);
		gl.glTexCoord2f(1.5f,0.5f); gl.glVertex3f(-0.75f, -0.29f, 1.05f);
		
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(-0.75f, -0.29f, 1.05f);
		gl.glTexCoord2f(0.0f,0.0f); gl.glVertex3f(-0.42f, -0.29f, 1.05f);
		gl.glTexCoord2f(0.25f,0.0f); gl.glVertex3f(-0.42f, -0.29f, 1.13f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(-0.75f, -0.29f, 1.13f);
		
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(-0.75f, -0.29f, 1.13f);
		gl.glTexCoord2f(0.0f,0.0f); gl.glVertex3f(-0.42f, -0.29f, 1.13f);
		gl.glTexCoord2f(0.25f,0.0f); gl.glVertex3f(-0.42f, -0.35f, 1.2f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(-0.75f, -0.35f, 1.2f);
		
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(-0.75f,-0.35f, 1.2f);
		gl.glTexCoord2f(0.0f,0.0f); gl.glVertex3f(-0.42f,-0.35f, 1.2f);
		gl.glTexCoord2f(0.25f,0.0f); gl.glVertex3f(-0.42f, -0.44f, 1.25f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(-0.75f, -0.44f, 1.25f);
		
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(-0.75f, -0.44f, 1.25f);
		gl.glTexCoord2f(0.0f,0.0f); gl.glVertex3f(-0.42f, -0.44f, 1.25f);
		gl.glTexCoord2f(0.25f,0.0f); gl.glVertex3f(-0.42f, -0.55f, 1.25f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(-0.75f, -0.55f, 1.25f);
		
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(-0.75f, -0.55f, 1.25f);
		gl.glTexCoord2f(0.0f,0.0f); gl.glVertex3f(-0.42f, -0.55f, 1.25f);
		gl.glTexCoord2f(0.25f,0.0f); gl.glVertex3f(-0.42f, -0.63f, 1.21f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(-0.75f, -0.63f, 1.21f);
		
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(-0.75f, -0.63f, 1.21f);
		gl.glTexCoord2f(0.0f,0.0f); gl.glVertex3f(-0.42f, -0.63f, 1.21f);
		gl.glTexCoord2f(0.25f,0.0f); gl.glVertex3f(-0.42f, -0.68f, 1.15f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(-0.75f, -0.68f, 1.15f);
		
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(-0.75f, -0.68f, 1.15f);
		gl.glTexCoord2f(0.0f,0.0f); gl.glVertex3f(-0.42f, -0.68f, 1.15f);
		gl.glTexCoord2f(1.25f,0.0f); gl.glVertex3f(-0.42f, -0.8f, 0.79f);
		gl.glTexCoord2f(1.25f,0.5f); gl.glVertex3f(-0.75f, -0.8f, 0.79f);
		
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(-0.75f, -0.8f, 0.79f);
		gl.glTexCoord2f(0.0f,0.0f); gl.glVertex3f(-0.42f, -0.8f, 0.79f);
		gl.glTexCoord2f(4f,0.0f); gl.glVertex3f(-0.42f, -0.8f, -0.70f);
		gl.glTexCoord2f(4f,0.5f); gl.glVertex3f(-0.75f, -0.8f, -0.70f);
		
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(-0.75f, -0.8f, -0.70f);
		gl.glTexCoord2f(0.0f,0.0f); gl.glVertex3f(-0.42f, -0.8f, -0.70f);
		gl.glTexCoord2f(1.0f,0.0f); gl.glVertex3f(-0.42f, -0.75f, -1.03f);
		gl.glTexCoord2f(1.0f,0.5f); gl.glVertex3f(-0.75f, -0.75f, -1.03f);
		
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(-0.75f, -0.75f, -1.03f);
		gl.glTexCoord2f(0.0f,0.0f); gl.glVertex3f(-0.42f, -0.75f, -1.03f);
		gl.glTexCoord2f(0.25f,0.0f); gl.glVertex3f(-0.42f, -0.72f, -1.11f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(-0.75f, -0.72f, -1.11f);
		
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(-0.75f, -0.72f, -1.11f);
		gl.glTexCoord2f(0.0f,0.0f); gl.glVertex3f(-0.42f, -0.72f, -1.11f);
		gl.glTexCoord2f(0.25f,0.0f); gl.glVertex3f(-0.42f, -0.65f, -1.16f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(-0.75f, -0.65f, -1.16f);
		
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(-0.75f, -0.65f, -1.16f);
		gl.glTexCoord2f(0.0f,0.0f); gl.glVertex3f(-0.42f, -0.65f, -1.16f);
		gl.glTexCoord2f(0.25f,0.0f); gl.glVertex3f(-0.42f, -0.55f, -1.16f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(-0.75f, -0.55f, -1.16f);
		
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(-0.75f, -0.55f, -1.16f);
		gl.glTexCoord2f(0.0f,0.0f); gl.glVertex3f(-0.42f, -0.55f, -1.16f);
		gl.glTexCoord2f(0.25f,0.0f); gl.glVertex3f(-0.42f, -0.45f, -1.11f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(-0.75f, -0.45f, -1.11f);
		
		gl.glTexCoord2f(0.0f,0.5f); gl.glVertex3f(-0.75f, -0.45f, -1.11f);
		gl.glTexCoord2f(0.0f,0.0f); gl.glVertex3f(-0.42f, -0.45f, -1.11f);
		gl.glTexCoord2f(0.25f,0.0f); gl.glVertex3f(-0.42f, -0.42f, -1.05f);
		gl.glTexCoord2f(0.25f,0.5f); gl.glVertex3f(-0.75f, -0.42f, -1.05f);
		gl.glEnd();
		gl.glPopMatrix();
		
		
		gl.glPopMatrix();
		gl.glPopMatrix();
		


		gl.glFlush();
		kat+=1.0f;
		if(kat>=360.0f)
			kat-=360.0f;
	}
	
	private void ObslugaKlawiatury() {
		Boolean zmiana=false;
		float angleStep=0.5f;
		float forwardStep=0.05f;
		if(keys[KeyEvent.VK_LEFT]){ alfa+=angleStep; zmiana=true;}
		if(keys[KeyEvent.VK_RIGHT]){ alfa-=angleStep; zmiana=true;}
		if(keys[KeyEvent.VK_UP]){ 
			if(obrotLufy > -25)
			 beta-=angleStep; zmiana=true;
			 }
		if(keys[KeyEvent.VK_DOWN]){
			if(obrotLufy < 10)
			 beta+=angleStep; zmiana=true;
			 }
		
		if(keys[KeyEvent.VK_A]){ charlie+=angleStep; zmiana=true;}
		if(keys[KeyEvent.VK_D]){ charlie-=angleStep; zmiana=true;}

		if(zmiana){
		
			obrotWiezy = alfa;
			obrotLufy = beta;
			obrotModeluPoziom = charlie;


		}
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {

	}

	@Override
	public void init(GLAutoDrawable drawable) {
		gl=drawable.getGL().getGL2();
		glu=GLU.createGLU(gl);
		glut=new GLUT();
		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		
		gl.glEnable(GL2.GL_CULL_FACE);
		float matSpec[]={1.0f,1.0f,1.0f,1.0f};
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, matSpec, 0);
		gl.glMateriali(GL2.GL_FRONT, GL2.GL_SHININESS, 128);
		
		gl.glEnable(GL2.GL_COLOR_MATERIAL);
		gl.glColorMaterial(GL2.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE);
		
		float ambientLight[]={0.8f,0.8f,0.8f,1.0f};
		gl.glLightModelfv(GL2.GL_LIGHT_MODEL_AMBIENT, ambientLight, 0);
	
		gl.glEnable(GL2.GL_LIGHTING);

		gl.glEnable(GL2.GL_NORMALIZE);
		
		////////////////shader
		
		ShaderCode vp=ShaderCode.create(gl, GL2.GL_VERTEX_SHADER, 1, this.getClass(), new String[]{"vs_tiger.glsl"}, true);
		ShaderCode fp=ShaderCode.create(gl, GL2.GL_FRAGMENT_SHADER, 1, this.getClass(), new String[]{"fs_tiger.glsl"}, true);
		ShaderProgram sp=new ShaderProgram();
		sp.add(gl, vp, System.err);
		sp.add(gl, fp, System.err);
		st=new ShaderState();
		st.attachShaderProgram(gl, sp, true);
		gl.glEnable(GL2.GL_BLEND);
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
		//////////////////////////////
		
		gl.glEnable(GL2.GL_TEXTURE_2D);
		try{
			File f=new File("Tiger_texture.png");
			Texture t=TextureIO.newTexture(f, true);
			texture=t.getTextureObject(gl);
		}catch(IOException e){
			e.printStackTrace();
		}
		try{
			File f=new File("track.png");
			Texture t=TextureIO.newTexture(f, true);
			texture1=t.getTextureObject(gl);
		}catch(IOException e){
			e.printStackTrace();
		}
		try{
			File f=new File("smoke2.jpg");
			Texture t=TextureIO.newTexture(f, true);
			texture2=t.getTextureObject(gl);
		}catch(IOException e){
			e.printStackTrace();
		}
		try{
			File f=new File("tlo.bmp");
			Texture t=TextureIO.newTexture(f, true);
			texture3=t.getTextureObject(gl);
		}catch(IOException e){
			e.printStackTrace();
		}
		String s=gl.glGetString(GL2.GL_RENDERER);
		setTitle(s);
	}
	

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		gl.glViewport(0, 0, width, height);
		if(height==0)
			height=1;
		float aspect=(float)width/height;
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluPerspective(45.0, aspect, 1.0, 10.0);
		glu.gluLookAt(0.0f, 0.0f, 5.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new Tygrys("p07");
			}
		});
	}

}
