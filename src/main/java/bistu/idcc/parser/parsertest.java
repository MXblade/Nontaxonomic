package bistu.idcc.parser;

import com.hankcs.hanlp.HanLP;

public class parsertest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(HanLP.segment("一种流体控制阀用的电气执行装置，用感应电机控制阀的开度，并具有一控制系统，它能检测实际开度和预定开度的偏差，根据开启偏差产生阀的开启和关闭速度指令，并检测实际开启或关闭速度与指令速度的偏差，根据该速度偏差产生转矩指令并用矢量计算法计算供给感应电机的初级电流。当阀处于完全关闭时，使感应电机断电并起动制动装置。装有使推力负载最佳的重新起动电混合动力汽车机的装置，还装有诊断反常现象的装置。"));
	}

}
