package ext3d.sys;


public final class Ctx {
	public static String    fillPrefix(int int_Length, Integer int_Value){
		return Str.combine(Str.repeat(int_Length-int_Value.toString().length(),0),int_Value);
	}
	public static boolean notSet(int int_Sequence, int... int_Bits){
		for(int m : int_Bits){
			if(mask(int_Sequence,m)){
				return false;
			}
		}
		return true;
	}
	public static boolean in(int int_Sequence, int... int_Bits){
		for(int m : int_Bits){
			if(mask(int_Sequence,m)){
				return true;
			}
		}
		return false;
	}
	public static boolean isSet(int int_Sequence, int... int_Bits){
		for(int m : int_Bits){
			if(!mask(int_Sequence,m)){
				return false;
			}
		}
		return true;
	}
	public static boolean   mask( int int_Sequence, int int_Mask){
		return (int_Sequence & int_Mask) == int_Mask;
	}
	public static boolean   mask( long lng_Sequence, long lng_Mask){
		return (lng_Sequence & lng_Mask) == lng_Mask;
	}
	public static int       moduloTo0(int int_Basis, int int_Divisor){
		return (int_Basis+(int_Basis % int_Divisor));
	}
}
