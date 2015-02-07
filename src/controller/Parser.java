package controller;

import com.sun.org.apache.bcel.internal.classfile.ClassFormatException;

public class Parser {
	/**
	 * Static class
	 * Only a list of parsing methods
	 */
	
	//All those methods return -10 if the string = "", 
		//Otherwise, it's the same (more or less) as Class.parseClass(..)
		/**
		 * 
		 * @param s
		 * @return 
		 */
		public static int parseInteg(String s){
			if(s.equals("")){
				return -10;
			}
			else{
				int result = (int) Double.parseDouble(s);
				if(Double.parseDouble(s) != result){
					Loader.erreurCount++;
					System.out.println("Attention dans parseInteg");
				}
				return result;
			}
		}
		
		/**
		 * 
		 * @param s
		 * @return
		 */
		public static byte parseByte2(String s){
			if(s.equals("")){
				return -10;
			}
			else{
				byte result = (byte) Double.parseDouble(s);
				if(Double.parseDouble(s) != result){System.out.println("Attention dans parseByte");}
				return result;
			}
		}
		
		public static Boolean parseBool(String s){
			if(s.equals("")){
				return null;
			}
			else{
				return Boolean.parseBoolean(s);
			}
		}
		
		public static double parseDouble2(String s){
			if(s.equals("")){
				return -10;
			}
			else{
				return Double.parseDouble(s);
			}
		}
		// End of Parsing methods
		
		/**
		 * 
		 * @param i
		 * @return 0 -> false ; 1 -> yes (easy right?) 
		 * @throws ClassFormatException
		 */
		public static Boolean intToBool(int i) throws ClassFormatException{
			if(i==0){
				return false;
			}
			else if(i==1){
				return true; 
			}
			else{
				Loader.erreurCount ++;
				return null;
			}
		}

}
