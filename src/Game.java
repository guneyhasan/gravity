import enigma.console.Console;
import enigma.core.Enigma;
import enigma.event.TextMouseEvent;
import enigma.event.TextMouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import enigma.console.TextAttributes;
import java.awt.Color;
import java.util.Random;

public class Game {
   public enigma.console.Console cn = Enigma.getConsole("Mouse and Keyboard");
   public TextMouseListener tmlis; 
   public KeyListener klis; 

   // ------ Standard variables for mouse and keyboard ------
   public int mousepr;          // mouse pressed?
   public int mousex, mousey;   // mouse text coords.
   public int keypr;   // key pressed?
   public int rkey;    // key   (for press/release)
   // ----------------------------------------------------
   
   Game() throws Exception {   // --- Contructor
                 
      // ------ Standard code for mouse and keyboard ------ Do not change
      tmlis=new TextMouseListener() {
         public void mouseClicked(TextMouseEvent arg0) {}
         public void mousePressed(TextMouseEvent arg0) {
            if(mousepr==0) {
               mousepr=1;
               mousex=arg0.getX();
               mousey=arg0.getY();
            }
         }
         public void mouseReleased(TextMouseEvent arg0) {}
      };
      cn.getTextWindow().addTextMouseListener(tmlis);
    
      klis=new KeyListener() {
         public void keyTyped(KeyEvent e) {}
         public void keyPressed(KeyEvent e) {
            if(keypr==0) {
               keypr=1;
               rkey=e.getKeyCode();
            }
         }
         public void keyReleased(KeyEvent e) {}
      };
      cn.getTextWindow().addKeyListener(klis);
      // ----------------------------------------------------
      



      //=====================================================================================================================
      //game part begins

      //creating map
      Object[][] map = new Object[25][55];
      for(int i = 0; i < 25; i++) {
         for(int j = 0; j < 55; j++) {
            if(j == 0 || j == 54 || i == 0 || i == 24) {
               map[i][j] = "#";
            }else if((i == 8 && j < 50) || (i == 16 && j > 4)) {
               map[i][j] = "#";
            }else {
               map[i][j] = ":";
            }
         }
      }
      Random rand = new Random();
      int count = 0;
      while (count < 180) {
         int row = rand.nextInt(24);
         int col = rand.nextInt(54);
         if (map[row][col] == ":") {
            map[row][col] = "0";
            count++;
         }
      }
      count = 0;
      while (count < 30) {
         int row = rand.nextInt(24);
         int col = rand.nextInt(54);
         if (map[row][col] == ":") {
            map[row][col] =  Integer.toString(rand.nextInt(1,4));
            count++;
         }
      }
      count = 0;
      while(count < 200) {
         int row = rand.nextInt(24);
         int col = rand.nextInt(54);
         if (map[row][col] == ":") {
            map[row][col] = " ";
            count++;
         }
      }
      for(int i = 0; i < 25; i++) {
         for(int j = 0; j < 55; j++) {
            if(map[i][j]==":"){
               TextAttributes earthsquaretextAttributes=new TextAttributes(Color.yellow,Color.yellow);
               cn.getTextWindow().output((String) map[i][j],earthsquaretextAttributes);
            }
            else if(map[i][j]=="0"){
               TextAttributes bouldertextAttributes=new TextAttributes(Color.lightGray,Color.gray);
               cn.getTextWindow().output("O",bouldertextAttributes);
            }
            else if(map[i][j]=="#"){
               TextAttributes walltextAttributes=new TextAttributes(Color.darkGray,Color.black);
               cn.getTextWindow().output("#",walltextAttributes);
            }
            else{
               System.out.print(map[i][j]);
            }
         }
         System.out.print("\n");
      }

      int px=1,py=1;
      boolean check=false;
      while(check=false){
         py=rand.nextInt(0,25);
         px= rand.nextInt(0,55);
         if(map[py][px]==":"){
            check=true;
         }
      }

      cn.getTextWindow().output(px,py,'P',new TextAttributes(Color.green));

      int time = 0;
      while(true) {
         int second=time/1000;
         String outputtime=Integer.toString(second);
         cn .getTextWindow().setCursorPosition(60,9);
         cn.getTextWindow().output("Time: "+outputtime);

         if(mousepr==1) {  // if mouse button pressed
            cn.getTextWindow().output(mousex,mousey,'#');  // write a char to x,y position without changing cursor position
            px=mousex; py=mousey;
            
            mousepr=0;     // last action  
         }
         if(keypr==1) {    // if keyboard button pressed

            cn.getTextWindow().output(px,py,' '); //clearing before position


            //movements
            try{
               if(rkey==KeyEvent.VK_LEFT) {
                  if(map[py][px-1]!="#" && map[py][px-1]!="0"){
                     px--;
                  }
               }
            }
            catch (Exception e){}

            try{
               if(rkey==KeyEvent.VK_RIGHT ) {
                  if(map[py][px+1]!="#" &&map[py][px+1]!="0"){
                     px++;
                  }
               }
            }
            catch (Exception e){}

            try{
               if(rkey==KeyEvent.VK_UP ) {
                  if(map[py-1][px]!="#" && map[py-1][px]!="0"){
                     py--;
                  }
               }
            }
            catch (Exception e){}

            try{
               if(rkey==KeyEvent.VK_DOWN ){
                  if(map[py+1][px]!="#"&& map[py+1][px]!="0"){
                     py++;
                  }
               }
            }catch (Exception e){}
            //--------------------------------------------
            char rckey=(char)rkey;
            //        left          right          up            down
            if(rckey=='%' || rckey=='\'' || rckey=='&' || rckey=='(') cn.getTextWindow().output(px,py, 'P',new TextAttributes(Color.green)); // VK kullanmadan test teknigi
            else cn.getTextWindow().output(rckey);

            
            if(rkey==KeyEvent.VK_SPACE) {
               String str;         
               str=cn.readLine();     // keyboardlistener running and readline input by using enter 
               cn.getTextWindow().setCursorPosition(5, 20);
               cn.getTextWindow().output(str);
            }
            
            keypr=0;    // last action  
         }


         time+=100;
         Thread.sleep(100);
      }
   }










}
