package main;
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

public class ReadWrite extends Thread
{


    Dynamixel dynamixel;
    Scanner scanner;




    // Control table address
    short ADDR_PRO_TORQUE_ENABLE        = 150;                // Control table address is different in Dynamixel model
    short ADDR_PRO_GOAL_POSITION        = 100;
    short ADDR_PRO_PRESENT_POSITION     = 45;

    // Protocol version
    int PROTOCOL_VERSION                = 2;                   // See which protocol version is used in the Dynamixel

    // Default setting
    byte DXL_ID                         = 1;                   // Dynamixel ID: 1
    int BAUDRATE                        = 152000;
    String DEVICENAME                   = "COM8";      // Check which port is being used on your controller
    // ex) "COM1"   Linux: "/dev/ttyUSB0"

    byte TORQUE_ENABLE                  = 1;                   // Value for enabling the torque
    byte TORQUE_DISABLE                 = 0;                   // Value for disabling the torque
    int DXL_MINIMUM_POSITION_VALUE      = -1500;             // Dynamixel will rotate between this value
    int DXL_MAXIMUM_POSITION_VALUE      = 1500;              // and this value (note that the Dynamixel would not move when the position value is out of movable range. Check e-manual about the range of the Dynamixel you use.)
    int DXL_MOVING_STATUS_THRESHOLD     = 20;                  // Dynamixel moving status threshold

    String KEY_FOR_ESCAPE               = "e";                 // Key for escape

    int COMM_SUCCESS                    = 0;                   // Communication Success result value
    int COMM_TX_FAIL                    = -1001;               // Communication Tx Failed




    int index = 0;
    int dxl_comm_result = COMM_TX_FAIL;                        // Communication result
    int[] dxl_goal_position = new int[]{DXL_MINIMUM_POSITION_VALUE, DXL_MAXIMUM_POSITION_VALUE};         // Goal position

    byte dxl_error = 0;                                        // Dynamixel error
    int dxl_present_position = 0;                              // Present position

    int port_num;


    public void init() {


        // Initialize Dynamixel class for java
        dynamixel = new Dynamixel();

        // Initialize PortHandler Structs
        // Set the port path
        // Get methods and members of PortHandlerLinux or PortHandlerWindows
        port_num = dynamixel.portHandler(DEVICENAME);

        // Initialize PacketHandler Structs
        dynamixel.packetHandler();

    }

    public void run() {

        while (true)
        {
            System.out.println("Press enter to continue! (or press e then enter to quit!)");
            if(scanner.nextLine().equals(KEY_FOR_ESCAPE))
                break;

            // Write goal position
            dynamixel.write4ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID, ADDR_PRO_GOAL_POSITION, dxl_goal_position[index]);


            byte[] move = new byte[4];

            // 주소 확인 요함 dynamixel.writePort(1, new byte[4] , 9);
            if ((dxl_comm_result = dynamixel.getLastTxRxResult(port_num, PROTOCOL_VERSION)) != COMM_SUCCESS)
            {
                // dynamixel.printTxRxResult(PROTOCOL_VERSION, dxl_comm_result);
                System.out.println(dynamixel.getTxRxResult(PROTOCOL_VERSION, dxl_comm_result));
            }
            else if ((dxl_error = dynamixel.getLastRxPacketError(port_num, PROTOCOL_VERSION)) != 0)
            {


                //dynamixel.printRxPacketError(PROTOCOL_VERSION, dxl_error);
                System.err.println(dynamixel.getRxPacketError(PROTOCOL_VERSION, dxl_error));
            }

            do
            {
                // Read present position
                dxl_present_position = dynamixel.read4ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID, ADDR_PRO_PRESENT_POSITION);
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

                System.out.printf("[ID: %d] GoalPos:%d  PresPos:%d\n", DXL_ID, dxl_goal_position[index], dxl_present_position);

            } while ((Math.abs(dxl_goal_position[index] - dxl_present_position) > DXL_MOVING_STATUS_THRESHOLD));

            // Change goal position
            if (index == 0)
            {
                index = 1;
            }
            else
            {
                index = 0;
            }
        }

    }


    public ReadWrite() {

        // Open port
        if (dynamixel.openPort(port_num))
        {
            System.out.println("Succeeded to open the port!");
        }
        else
        {
            System.out.println("Failed to open the port!");
            System.out.println("Press any key to terminate...");
            scanner.nextLine();
            return;
        }

        // Set port baudrate
        if (dynamixel.setBaudRate(port_num, BAUDRATE))
        {
            System.out.println("Succeeded to change the baudrate!");
        }
        else
        {
            System.out.println("Failed to change the baudrate!");
            System.out.println("Press any key to terminate...");
            scanner.nextLine();
            return;
        }

        // Enable Dynamixel Torque
        dynamixel.write1ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID, ADDR_PRO_TORQUE_ENABLE, TORQUE_ENABLE);
        if ((dxl_comm_result = dynamixel.getLastTxRxResult(port_num, PROTOCOL_VERSION)) != COMM_SUCCESS)
        {
            //dynamixel.printTxRxResult(PROTOCOL_VERSION, dxl_comm_result);
            System.out.println(dynamixel.getTxRxResult(PROTOCOL_VERSION, dxl_comm_result));
        }
        else if ((dxl_error = dynamixel.getLastRxPacketError(port_num, PROTOCOL_VERSION)) != 0)
        {
            System.err.println(dynamixel.getRxPacketError(PROTOCOL_VERSION, dxl_error));
        }
        else
        {
            System.out.println("Dynamixel has been successfully connected");
        }


        // Disable Dynamixel Torque
        dynamixel.write1ByteTxRx(port_num, PROTOCOL_VERSION, DXL_ID, ADDR_PRO_TORQUE_ENABLE, TORQUE_DISABLE);
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

        // Close port
        dynamixel.closePort(port_num);
    }


    public static void main(String[] args)
    {

        ReadWrite rw = new ReadWrite();
        //scanner = new Scanner(System.in);
        rw.init();



        return;
    }



}