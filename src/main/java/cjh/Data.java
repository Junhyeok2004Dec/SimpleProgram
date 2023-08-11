package cjh;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;
import java.io.RandomAccessFile;

//Spagetti!
public class Data {

    boolean change = false;
	byte OPERATING_MODE = 1;

//deg : n시방향 (deg = n -> n시방향);
	int deg = 0;

    int movement;
    public Object obj, obj1, obj2;
    Image image;
    
    Position position;
    ArrayList<String> sensorData = new ArrayList<>();
    
    private int[] Vector; // get Vector from two var(integer, by file input stream)
    String[] splitData, splitData2 ,splitData3 ; 
    int mod = 0;

    public ArrayList<String> outputs;

    SysLog log = new SysLog();

    public Data() {

    }


    public Data(ArrayList<String> sensorData, Image image, Object obj) {
        this.image = image;
        this.sensorData = sensorData;
        this.obj = obj;
    }

    public void addVar(float var) {
        this.sensorData.add(String.valueOf(var));

    }

    //포지션 -> get Vector(2 - dimension -> get )
    public void getVector2f(int e1, int e2) {

        this.Vector[0] = e1;
        this.Vector[1] = e2;

    }


    public Data getData() {
        return this;
    }

    public Image getImage() {
        return image;
    }

    public Position getPosition() {
        return position;
    }


    public void setPosition(int e1, int e2) {

        this.position = new Position(e1, e2);
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }


    public void returnData() {
        // 규격
        //
        try {
            log.init();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String txt = "";

        for (String data : sensorData) {
            txt += data;
            txt += ",";
        }

        log.log(txt);


    }




String lastLine, sCurrentLine;
    public void txtToObjFile(File file) {
        // 추후 2차원 배열로 수정가능하나 개수가 적으므로 존치.
        ArrayList<String> sensor;
        BufferedReader br;
        String textstring;

        Object temp1, temp2;
        try {

            br = new BufferedReader(new FileReader(tempData.path5));
            this.obj = new String(Files.readAllBytes(Paths.get(tempData.path)));
            this.obj1 = new String(Files.readAllBytes(Paths.get(tempData.path4)));

            lastLine = "";

            while ((sCurrentLine = br.readLine()) != null) 
            {
                System.out.println(sCurrentLine);
                lastLine = sCurrentLine;
            }
            
            this.obj2 = lastLine;
            splitData = (String.valueOf(this.obj)).split(",");
            splitData2 = (String.valueOf(this.obj1)).split(",");
            splitData3 = (String.valueOf(this.obj2)).split(",");
                


            



                

                this.position = new Position(Integer.parseInt(splitData[0]), Integer.parseInt(splitData[1]));
                this.sensorData = (new ArrayList<>(Arrays.asList(splitData)));
                this.sensorData.add(String.valueOf(new ArrayList<>(Arrays.asList(splitData2))));
                this.sensorData.add(String.valueOf(new ArrayList<>(Arrays.asList(splitData3))));


				Double degvar =( (float) Math.atan2(Integer.parseInt(splitData[0]), Integer.parseInt(splitData[1]))) * ((180/Math.PI));
				
				
				//4방위, 오차 45도
				if( (45 > degvar ) || (degvar >= -45)) { //0번(동)
					this.deg = 0;
				} else if ((135 > degvar )||(degvar >= 45)) { //(북)
					this.deg = 1;
				} else if ((degvar >= 135) || (degvar < -135)) { //(서)
					this.deg = 2;
				} else if (degvar < -45) { //(남)
					this.deg = 3;
				} 
				
				
				

            
        } catch (EOFException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }





        returnData();

    }


    public int getMovement() {
        return this.movement;
    }
    public ArrayList<String> getSensorData() {
        return this.sensorData;
    }

    public void setMovement(int mo) {
        this.movement += mo;

    }

    @Override
    public String toString() {
        String a;

        a = "image : " + image.getSource().toString() + sensorData.toString();
        return a;
    }

    public void output(String data) {
        //File output -> to Log (use Logger)

        outputs.add(data);


    }
}
