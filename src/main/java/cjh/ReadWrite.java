package cjh;
/*
 * ReadWrite.java
 *
 *  Created on: 2016. 6. 23.
 *      Author: Ryu Woon Jung (Leon)
 */

//
// *********     Read and Write Example      *********
//
//
// Available main.Dynamixel model on this example : All models using Protocol 2.0
// This example is designed for using a main.Dynamixel PRO 54-200, and an USB2DYNAMIXEL.
// To use another main.Dynamixel model, such as X series, see their details in E-Manual(support.robotis.com) and edit below variables yourself.
// Be sure that main.Dynamixel PRO properties are already set as %% ID : 1 / Baudnum : 3 (Baudrate : 1000000 [1M])
//

import java.util.Scanner;

public class ReadWrite extends Thread {


	//모터 제어량(속도)
	public int velo;
	public Dynamixel dynamixel;
	Scanner scanner = new Scanner(System.in);
	// Control table address
	public short ADDR_PRO_TORQUE_ENABLE = 64;               // Control table address is different in Dynamixel model
	public short ADDR_PRO_GOAL_POSITION = 116;
	public short ADDR_PRO_PRESENT_POSITION = 132;
	short ADDR_PRO_PRESENT_VELOCITY = 128;
	public short ADDR_PRO_GOAL_VELOCITY = 104;
	public short ADDR_OPERATING_MODE = 11;
	// Protocol version
	public int PROTOCOL_VERSION = 2;                   // See which protocol version is used in the Dynamixel
	// Default setting
	public byte DXL_ID = 1;                   // Dynamixel ID: 1
	public byte DXL_ID2 = 2;
	int BAUDRATE = 115200;
	// ex) "COM1"   Linux: "/dev/ttyUSB0"
	String DEVICENAME = "COM4";      // Check which port is being used on your controller
	public byte TORQUE_ENABLE = 1;                   // Value for enabling the torque
	public byte TORQUE_DISABLE = 0;                   // Value for disabling the torque
	/*
	 0 : Current Control
	 1 : Velocity Ctrl
	 2 :
	 3 : Posit Ctrl
	 4 : Extend' Posit Ctrl
	 5 : Current Pos

	 */
	public int DXL_MINIMUM_POSITION_VALUE = 100;             // Dynamixel will rotate between this value
	public int DXL_MAXIMUM_POSITION_VALUE = 1100;              // and this value (note that the Dynamixel would not move when the position value is out of movable range. Check e-manual about the range of the Dynamixel you use.)
	public int DXL_MOVING_STATUS_THRESHOLD = 20;                  // Dynamixel moving status threshold
	String KEY_FOR_ESCAPE = "e";                 // Key for escape
	int COMM_SUCCESS = 0;                   // Communication Success result value
	int COMM_TX_FAIL = -1001;               // Communication Tx Failed
	int index = 0;
	int dxl_comm_result = COMM_TX_FAIL;                        // Communication result


	int[] dxl_goal_position = new int[]{DXL_MINIMUM_POSITION_VALUE, DXL_MAXIMUM_POSITION_VALUE};         // Goal position


	byte dxl_error = 0;                                        // Dynamixel error


	int dxl_present_position = 0;                              // dest position
	int dxl_present_velo = 0;

	int dxl_present_position2 = 0;                              // dest position id2
	int dxl_present_velo2 = 0;      //id2

	//추후 배열 관련 코드로 수정예정


	public int port_num;



	SysLog log;


	private Data data;

	public ReadWrite(Data data) {
		this.data = data;
	}


	public void init() {
		data.setMovement(0);
	}


	@Override
	public void run() {


		try{
			dynamixel = new Dynamixel();


		port_num = dynamixel.portHandler(DEVICENAME);

		// Initialize PacketHandler Structs
		dynamixel.packetHandler();



		dynamixel.write1ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID, ADDR_OPERATING_MODE, data.OPERATING_MODE);//모드 설정(오퍼레이팅 모드)
		dynamixel.write1ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID, ADDR_PRO_TORQUE_ENABLE, TORQUE_ENABLE);

		dynamixel.write1ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID2, ADDR_OPERATING_MODE, data.OPERATING_MODE);//모드 설정(오퍼레이팅 모드)
		dynamixel.write1ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID2, ADDR_PRO_TORQUE_ENABLE, TORQUE_ENABLE);






		if ((dxl_comm_result = dynamixel.getLastTxRxResult(port_num, PROTOCOL_VERSION)) != COMM_SUCCESS) {
			//dynamixel.printTxRxResult(PROTOCOL_VERSION, dxl_comm_result);
			System.out.println(dynamixel.getTxRxResult(PROTOCOL_VERSION, dxl_comm_result));
		} else if ((dxl_error = dynamixel.getLastRxPacketError(port_num, PROTOCOL_VERSION)) != 0) {


			System.err.println(dynamixel.getRxPacketError(PROTOCOL_VERSION, dxl_error));

			log.err(dynamixel.getRxPacketError(PROTOCOL_VERSION, dxl_error));


		} else {
			System.out.println("Dynamixel has been successfully connected");
		}


		while (data.mod != -1) {
		dynamixel.write1ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID, ADDR_OPERATING_MODE, data.OPERATING_MODE);//모드 설정(오퍼레이팅 모드)
		dynamixel.write1ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID2, ADDR_OPERATING_MODE, data.OPERATING_MODE);//모드 설정(오퍼레이팅 모드)

			dynamixel.write1ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID, ADDR_PRO_TORQUE_ENABLE, TORQUE_ENABLE);

			dynamixel.write1ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID2, ADDR_PRO_TORQUE_ENABLE, TORQUE_ENABLE);

			try {
				Thread.sleep(100);

			} catch (InterruptedException e) {
				log.err(e.getMessage());
				e.printStackTrace();
			}




			program();





			if(data.mod == 0) {



				if(data.change) {
					dynamixel.write1ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID, ADDR_PRO_TORQUE_ENABLE, TORQUE_DISABLE);
					dynamixel.write1ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID2, ADDR_PRO_TORQUE_ENABLE, TORQUE_DISABLE);

					dynamixel.write1ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID, ADDR_OPERATING_MODE, (byte) 1);//모드 설정(오퍼레이팅 모드)
					dynamixel.write1ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID2, ADDR_OPERATING_MODE, (byte) 1);//모드 설정(오퍼레이팅 모드)

					dynamixel.write1ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID, ADDR_PRO_TORQUE_ENABLE, TORQUE_ENABLE);
					dynamixel.write1ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID2, ADDR_PRO_TORQUE_ENABLE, TORQUE_ENABLE);

					data.change = false;
				}




				if (data.getMovement() > 0) {
					System.out.println("온!");
					dynamixel.write4ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID, ADDR_PRO_GOAL_VELOCITY, (-1)*data.getMovement());
					dynamixel.write4ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID2, ADDR_PRO_GOAL_VELOCITY, data.getMovement());
				}else {
					System.out.println("오프");
					dynamixel.write4ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID2, ADDR_PRO_GOAL_VELOCITY, (-1)*data.getMovement());
					dynamixel.write4ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID, ADDR_PRO_GOAL_VELOCITY, data.getMovement());

				}
			}
			if(data.mod == 1) {


				if (data.change) {
					dynamixel.write1ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID, ADDR_PRO_TORQUE_ENABLE, TORQUE_DISABLE);
					dynamixel.write1ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID2, ADDR_PRO_TORQUE_ENABLE, TORQUE_DISABLE);

					dynamixel.write1ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID, ADDR_OPERATING_MODE, (byte) 4);//모드 설정(오퍼레이팅 모드)
					dynamixel.write1ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID2, ADDR_OPERATING_MODE, (byte) 4);//모드 설정(오퍼레이팅 모드)

					dynamixel.write1ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID, ADDR_PRO_TORQUE_ENABLE, TORQUE_ENABLE);
					dynamixel.write1ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID2, ADDR_PRO_TORQUE_ENABLE, TORQUE_ENABLE);
					data.change = false;
				}


				if(data.deg == 0) { 

							// 오른쪽 당김
							dynamixel.write4ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID, ADDR_PRO_GOAL_POSITION, 10000);
							data.deg = -2;

}
			
					else if(data.deg == 1) {  // 위쪽
				//변동사항 없음
			}
			
					else if(data.deg == 2) {  
					
							// 왼쪽 당김
							dynamixel.write4ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID, ADDR_PRO_GOAL_POSITION, -5000);
							dynamixel.write4ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID2, ADDR_PRO_GOAL_POSITION, 5000);

							data.deg = -2;


					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						log.err(e.getMessage());
						throw new RuntimeException(e);
					}

				}
					else if(data.deg == 3) {  // 뒤로 돌앗
						
						
							dynamixel.write4ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID2, ADDR_PRO_GOAL_VELOCITY, -10000);
							data.deg = -2;

			}		else if (data.deg == -2) // 보류
			{
				dynamixel.write4ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID, ADDR_PRO_GOAL_POSITION, 0);
				dynamixel.write4ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID2, ADDR_PRO_GOAL_POSITION, 0);
				
			}
			}
			
			if ((dxl_comm_result = dynamixel.getLastTxRxResult(port_num, PROTOCOL_VERSION)) != COMM_SUCCESS) {
				// dynamixel.printTxRxResult(PROTOCOL_VERSION, dxl_comm_result);
				System.out.println(dynamixel.getTxRxResult(PROTOCOL_VERSION, dxl_comm_result));
			} else if ((dxl_error = dynamixel.getLastRxPacketError(port_num, PROTOCOL_VERSION)) != 0) {


				//dynamixel.printRxPacketError(PROTOCOL_VERSION, dxl_error);
				System.err.println(dynamixel.getRxPacketError(PROTOCOL_VERSION, dxl_error) + "에러");
			}


			//get current Velocity
			dxl_present_velo = dynamixel.read4ByteTxRx(port_num, PROTOCOL_VERSION,
					DXL_ID, ADDR_PRO_GOAL_VELOCITY);

			if ((dxl_comm_result = dynamixel.getLastTxRxResult(port_num, PROTOCOL_VERSION)) != COMM_SUCCESS) {
				System.err.println(dynamixel.getTxRxResult(PROTOCOL_VERSION, dxl_comm_result) + "에러");
			}
			//System.out.printf("[ID: %d] velocity - %d\n", DXL_ID, dxl_present_velo);



			do {

				if (data.OPERATING_MODE != 3) {
					break;
				}


				// Read present position
				dxl_present_position = dynamixel.read4ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID, ADDR_PRO_PRESENT_POSITION);
				if ((dxl_comm_result = dynamixel.getLastTxRxResult(port_num, PROTOCOL_VERSION)) != COMM_SUCCESS) {
					// dynamixel.printTxRxResult(PROTOCOL_VERSION, dxl_comm_result);
					System.out.println(dynamixel.getTxRxResult(PROTOCOL_VERSION, dxl_comm_result));
				} else if ((dxl_error = dynamixel.getLastRxPacketError(port_num, PROTOCOL_VERSION)) != 0) {
					//dynamixel.printRxPacketError(PROTOCOL_VERSION, dxl_error);

					System.out.println(dynamixel.getRxPacketError(PROTOCOL_VERSION, dxl_error));
				}

				int a = dynamixel.read1ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID, ADDR_PRO_TORQUE_ENABLE);
				System.out.println("d" + a + "\n");

				System.out.printf("[ID: %d] GoalPos:%d  PresPos:%d\n", DXL_ID, dxl_goal_position[index], dxl_present_position);

			} while ((Math.abs(dxl_goal_position[index] - dxl_present_position) > DXL_MOVING_STATUS_THRESHOLD));

			// Change goal position
			if (index == 0) {
				index = 1;
			} else {
				index = 0;
			}

			if(Thread.interrupted()) {
				break;
			}
		}




		dynamixel.write1ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID, (short) 64, (byte) 0);
		dynamixel.write1ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID2, (short) 64, (byte) 0);
		} catch(Exception e) {
			log.err(e.getMessage());
			e.printStackTrace();
		}

	}


	public void program() {

		// Open port
		if (dynamixel.openPort(port_num)) {
			//System.out.println("Succeeded to open the port!");
		} else {
			System.out.println("Failed to open the port!");
			System.out.println("Press any key to terminate...");
			scanner.nextLine();
			return;
		}

		// Set port baudrate
		if (dynamixel.setBaudRate(port_num, BAUDRATE)) {
			//System.out.println("Succeeded to change the baudrate!");
		} else {
			System.out.println("Failed to change the baudrate!");
			System.out.println("Press any key to terminate...");
			scanner.nextLine();
			return;
		}

		// Enable Dynamixel Torque



		// Disable Dynamixel Torque

        /*dynamixel.write1ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID, ADDR_PRO_TORQUE_ENABLE, TORQUE_DISABLE);
        if ((dxl_comm_result = dynamixel.getLastTxRxResult(port_num, PROTOCOL_VERSION)) != COMM_SUCCESS)
        {
            // dynamixel.printTxRxResult(PROTOCOL_VERSION, dxl_comm_result);
            System.out.println(dynamixel.getTxRxResult(PROTOCOL_VERSION, dxl_comm_result));
        }
        else if ((dxl_error = dynamixel.getLastRxPacketError(port_num, PROTOCOL_VERSION)) != 0)
        {
            //dynamixel.printRxPacketError(PROTOCOL_VERSION, dxl_error);
            System.out.println(dynamixel.getRxPacketError(PROTOCOL_VERSION, dxl_error));
        }

*/
	}


	public void changeVelocity(int amount) {
		velo = amount;

	}

	public void accelerate(int amount) {
		velo += amount;
	}



}