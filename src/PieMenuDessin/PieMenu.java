package PieMenuDessin;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


/* 1. creation piemenu
 * 
 * 2. ajout menu principal * X
 *    ajout menu secondaire * X
 * 
 * 3. attente affichage
 */

public class PieMenu {

	protected ArrayList<PieItem> itemsList = null;
	protected PieMenu activatedPie= null;
	
	protected int posX = 0;
	protected int posY = 0;
	
	public PieMenu(){
		itemsList = new ArrayList<PieItem>();
		activatedPie = this;
	}
	
	public void addMenuItem(BufferedImage icon, PieMenu son, PtrFunc ptrFun){
		itemsList.add(new PieItem(icon,son,ptrFun));
	}
	
	public void setPositionItems(int x0, int y0){
		if (activatedPie == this){
			posX = x0;
			posY = y0;
			System.out.println("test pos: " + x0 + " " + y0);
			double degree = Math.toRadians(360/itemsList.size());
			double x = x0 + 150;
			double y = y0;
			double a = x0;
			double b = y0;
			for ( int i=0; i < itemsList.size(); i++ ){
		        double dirP1 = Math.atan2(b-y, a-x);
		        double dirP2 = dirP1 + degree*i;
		        double dist = 150 ;
		        //r.orientTo( -1 * (120-degree*i) * Math.PI/4);
		        itemsList.get(i).setPosition( (int) (a + dist*Math.cos(dirP2)) , (int) (b + dist*Math.sin(dirP2)) );
			}
		}else{
			activatedPie.setPositionItems( x0, y0);
		}
		
	}
	
	public void draw(Graphics g){
		for ( PieItem pm: itemsList){
			pm.draw(g);
		}
	}
	
	public void drawPieMenu(Graphics g){
		if (activatedPie == this){
			draw(g);
		}else{
			activatedPie.drawPieMenu(g);
		}
	}

	public void collision(int x, int y) {
		if (activatedPie == this){
			for ( PieItem pm: itemsList){
				if ( pm.collision(x, y) ){
					return;
				}
			}
		}else{
			activatedPie.collision(x, y);
		}
	}
	
	public void reset(){
		if (activatedPie == this){
			return;
		}else{
			activatedPie.reset();
			activatedPie = this;
			return;
		}
	}
	
	public class PieItem{
		protected BufferedImage icon;
		protected PieMenu son;
		protected PtrFunc ptrFun;
		
		protected int x;
		protected int y;
		
		public PieItem(BufferedImage icon, PieMenu son, PtrFunc ptrFun){
			this.icon = icon;
			this.son = son;
			this.ptrFun = ptrFun;
		}
		
		public void setPosition(int x, int y){
			this.x = x - icon.getWidth()/2;
			this.y = y - icon.getHeight()/2;
		}
		
		public void draw(Graphics g){
			g.drawImage( icon, x , y, null);
		}
		
		public boolean collision(int x0, int y0){
			if ( x0 > x && x0 < x+icon.getWidth() && y0 > y && y0 < y+icon.getHeight()  ){
				if ( son != null ){
					activatedPie = son;
					activatedPie.setPositionItems(x0, y0);
				}
				if ( ptrFun != null ){ ptrFun.func(); }
				return true;
			}else{
				return false;
			}
		}
	}
}
