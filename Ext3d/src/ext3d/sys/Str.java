package ext3d.sys;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Collection;


public final class Str {
	public static boolean   cmp(Object obj_A, Object obj_B){
		return (Str.valueOf(obj_A).equals(Str.valueOf(obj_B))) || Str.valueOf(obj_A).equals(Str.valueOf(obj_B));
	}
	public static String    valueOf(Object obj_String){
		return String.valueOf(obj_String);
	}
	public static String    combine(Object... obj_Parts){
		StringBuilder sb_Ret = new StringBuilder();
		for(Object o : obj_Parts){
			sb_Ret.append(o);
		}
		return sb_Ret.toString();
	}
	public static boolean   isNull(Object... obj_Parts){
		for(Object o : obj_Parts){
			if(o != null){
				return false;
			}
		}
		return true;
	}
	public static <T>String loop(Collection<T> list, ForEach<T> fe_Action){
		StringBuilder sb = new StringBuilder();
		for(T t : list){
			sb.append(fe_Action.run(t));
		}
		return sb.toString();
	}
	public static String    words(Object... obj_Parts){
		return csv(" ", obj_Parts);
	}
	public static String    list(Object obj_Delimiter, Object obj_Block_Start, Object obj_Block_End, Object... obj_Values){
		StringBuilder sb_Ret = new StringBuilder();
		for(int i = 0; i < obj_Values.length; i++){
			sb_Ret
					.append(i > 0 ? obj_Delimiter : "")
					.append(eatNull(obj_Block_Start))
					.append(obj_Values[i])
					.append(eatNull(obj_Block_End));
		}
		return sb_Ret.toString();
	}
	public static String    list(Object obj_Delimiter, Object obj_Block_Start, Object obj_Block_End, Collection obj_Values){
		StringBuilder sb_Ret = new StringBuilder();
		int i = 0;
		for(Object o : obj_Values){
			sb_Ret
					.append(i > 0 ? obj_Delimiter : "")
					.append(eatNull(obj_Block_Start))
					.append(o)
					.append(eatNull(obj_Block_End));
			i++;
		}
		return sb_Ret.toString();
	}
	public static String    csv(Object obj_Delimiter, Object... obj_Parts){
		StringBuilder sb_Ret = new StringBuilder();
		for(int i = 0; i < obj_Parts.length; i++){
			if(i > 0){
				sb_Ret.append(obj_Delimiter);
			}
			sb_Ret.append(obj_Parts[i]);
		}
		return sb_Ret.toString();
	}
	public static String    csv(Object obj_Delimiter, Collection obj_Parts){
		StringBuilder sb_Ret = new StringBuilder();
		int i = 0;
		for(Object o : obj_Parts){
			if(i > 0){
				sb_Ret.append(obj_Delimiter);
			}
			sb_Ret.append(o);
			i++;
		}
		return sb_Ret.toString();
	}
	public static String    makeNullNumeric(Object str_Target){
		return (str_Target+"").toUpperCase().matches("NULL") || !str_Target.toString().matches("[0-9]+") ? "0" : str_Target.toString();
	}
	public static String    eatNull(Object str_Target){
		return valueOf(str_Target).toUpperCase().matches("NULL")? "" : str_Target.toString();
	}
	public static String    repeat(int int_Count, Object str_Sequence){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < int_Count; i++){
			sb.append(str_Sequence);
		}
		return sb.toString();
	}
	public static String    leadZero(int int_Base,int int_Value){
		String b = String.valueOf(int_Base);
		String v = String.valueOf(int_Value);
		return combine(repeat(b.length() - v.length(), "0"), v);
	}
	public static String[]  array(Object... obj_Values){
		String[] str_Ret = new String[obj_Values.length];
		int c = 0;
		for(Object o : obj_Values){
			str_Ret[c++] = Str.valueOf(o);
		}
		return str_Ret;
	}
	public static String    alternate(Object... obj_Fallbacks){
		for(int i = 0; i < obj_Fallbacks.length; i ++){
			if(!eatNull(obj_Fallbacks[i]).isEmpty()){
				return Str.valueOf(obj_Fallbacks[i]);
			}
		}
		return "";
	}
	public static String    ifValueless(Object obj_Base, Object obj_Alternative){
		return eatNull(obj_Base).isEmpty() ? valueOf(obj_Alternative) : valueOf(obj_Base);
	}
	public static String    args(String s, Object... obj_Values){
		return String.format(s, obj_Values);
	}
	public static float     toFlt(Object str_Target){
		try{
			return Float.parseFloat(str_Target.toString());
		}catch(Exception e) {
			return 0;
		}
	}
	public static double    toDbl(Object str_Target){
		try{
			return Double.parseDouble(str_Target.toString());
		}catch(Exception e) {
			return 0;
		}
	}
	public static String    toMoney(Object str_Target){
		DecimalFormatSymbols decx = new DecimalFormatSymbols();
		decx.setDecimalSeparator(',');
		decx.setGroupingSeparator('.');
		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", decx);
		return decimalFormat.format(toDbl(str_Target));
	}
	public static String    toLenght(Object str, int int_Lenght, Object str_Filler){
		String s = eatNull(str);
		String f = eatNull(str_Filler);
		if(s.length() > int_Lenght){
			s = trim(int_Lenght,s);
		}else{
			s = Str.combine(s,repeat(int_Lenght-s.length(),f));
		}
		return s;
	}
	public static int       toInt(Object str_Target){
		try {
			if(str_Target != null) {
				String s = str_Target.toString();
				s = s.replaceAll("(\\..+)|(,.+)","");
				return Integer.parseInt(s);
			}
		}catch(Exception e){}
		return 0;
	}
	public static long      toLng(Object str_Target){
		try{
			return Long.parseLong(valueOf(str_Target));
		}catch(Exception e){
			return 0;
		}
	}
	public static boolean   toBln(Object str_Target){
		return str_Target != null && str_Target.toString().toUpperCase().matches("T|J|Y|TRUE|JA|YES");
	}
	public static String    read(InputStream ipt_Res) throws Exception{
		StringBuilder sb_Ret = new StringBuilder();
		int int_Char = -1;
		while( (int_Char = ipt_Res.read()) != -1 ){
			sb_Ret.append((char)int_Char);
		}
		ipt_Res.close();
		return sb_Ret.toString();
	}
	public static String    encrypt(String str_XorBasic, Object str_Target){
		char[] chr_Password     = str_XorBasic.toCharArray();
		char[] chr_Target       = str_Target.toString().toCharArray();
		int int_Password_Lenght = chr_Password.length;
		int int_Current_Key     = 0;
		for(int i = 0; i < chr_Target.length; i++){
			int_Current_Key = int_Current_Key < int_Password_Lenght ? int_Current_Key : 0;
			chr_Target[i]   = (char) (((int)chr_Target[i]) ^ ((int)chr_Password[int_Current_Key++]));
		}
		return new StringBuilder().append(chr_Target).toString();
	}
	public static String    fromInputStream(InputStream in_LargeText){
		try {
			String str_Ln;
			BufferedReader br = new BufferedReader(new InputStreamReader(in_LargeText,"UTF-8"));
			StringBuilder sb_Lines = new StringBuilder();
			while((str_Ln = br.readLine()) != null){
				sb_Lines.append(str_Ln);
			}
			return sb_Lines.toString();
		}catch(Exception e){
			return null;
		}
	}
	public static String    trim(int int_Max, String str_Line){
		if(int_Max < 3 || str_Line.length() <= int_Max){
			return str_Line;
		}else{
			return combine(str_Line.substring(0,int_Max-3),"...");
		}
	}
	public static int       maxLength(Collection obj_Values){
		int int_Ret = 0;
		for(Object o : obj_Values){
			int_Ret = o != null && o.toString().length() > int_Ret ? o.toString().length() : int_Ret;
		}
		return int_Ret;
	}
	public interface        ForEach<T>{
		public String run(T o);
	}
}
