///**
// * @author ${Mark}
// *
// * 
// */
//
//package Kollisionen;
//
//import Anzeige.IMenu;
//import Anzeige.Menu;
//
//public class Kollision implements IKollision, IMenu {
//	public static boolean kollidiertSepman  = false;
//	public static boolean kollidiertTracer  = false;
//	public static boolean kollidiertDefender=false;
//	public static boolean kollidiertRandom  = false;
//	public boolean pause = true;
//	public int leben ;
//	
//	public Kollision(){
//		super();
//	}
//
//	
//	
//	public void kollisionserkennung(){
//
//		while(pause == false){
//			if ( kollidiertSepman ==true ){
//				if (leben>1){
//				setzePausenBildschrim();
//				leben -=1;
//				}
//				else{
//					setzeNiederlageBildschirm();
//				}
//			}
//			else if( kollidiertTracer ==true){
//				if (leben>1){
//					setzePausenBildschrim();
//					leben -=1;
//					}
//					else{
//						setzeNiederlageBildschirm();
//					}
//			}
//			else if (kollidiertDefender == true){
//				if (leben>1){
//					setzePausenBildschrim();
//					leben -=1;
//					}
//					else{
//						setzeNiederlageBildschirm();
//					}
//			}
//			else if(kollidiertRandom ==true)
//				if (leben>1){
//					setzePausenBildschrim();
//					leben -=1;
//					}
//					else{
//						setzeNiederlageBildschirm();
//					}
//		}
//	}
//		
//	public void setPause(){
//		if (pause){
//			pause = false;
//		}
//		else{
//			pause = true;
//		}
//	}
//	
//	public void setLeben( int i){
//		leben = i;
//	}
//
//	@Override
//	public void initComponents() {
//		// TODO Auto-generated method stub
//		// Wird nicht verwendet
//		
//	}
//
//	@Override
//	public void setzeStartbildschirm() {
//		// TODO Auto-generated method stub
//		// Wird nicht verwendet
//		
//	}
//
//	@Override
//	public void setzeAuswahldisplay() {
//		// TODO Auto-generated method stub
//		// Wird nicht verwendet
//		
//	}
//
//	@Override
//	public void setzeSpielvorbereitungsdisplay() {
//		// TODO Auto-generated method stub
//		// Wird nicht verwendet
//		
//	}
//
//	@Override
//	public void setzeSiegbildschirm() {
//		// TODO Auto-generated method stub
//		// Wird nicht verwendet
//		
//	}
//
//	@Override
//	public void setzeNiederlageBildschirm() {
//		
//		// TODO Auto-generated method stub
//	 
//		
//	}
//
//	@Override
//	public void setzePausenBildschrim() {
//		// TODO Auto-generated method stub
//		// Wird nicht verwendet
//		
//	}
//	@Override
//	public int getTaste() {
//		// TODO Auto-generated method stub
//		// Wird nicht verwendet
//		return 0;
//	}  
//}
