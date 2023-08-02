package cjh;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;

public class Data {

    boolean change = false;
	byte OPERATING_MODE = 1;

//deg : n시방향 (deg = n -> n시방향);
	int deg = 0;

    int movement;
    public Object obj;
    Image image;
    Position position;
    private ArrayList<String> sensorData = new ArrayList<>();
    private int[] Vector; // get Vector from two var(integer, by file input stream)

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



    public void txtToObjFile(File file) {
        String[] splitData, sd2 ,sd3 ; // 추후 2차원 배열로 수정가능하나 개수가 적으므로 존치.
        ArrayList<String> sensor;
        BufferedReader br;
        String textstring;

        Object temp1, temp2;
        try {


            this.obj = new String(Files.readAllBytes(Paths.get(tempData.path)));
            temp1 = new String(Files.readAllBytes(Paths.get(tempData.path4)));
            temp2 = new String(Files.readAllBytes(Paths.get(tempData.path5)));

            splitData = (String.valueOf(this.obj)).split(",");
            //sensor = new ArrayList<>(Arrays.asList(splitData));

            sd2 = (String.valueOf(temp1)).split(",");
            sd3 = (String.valueOf(temp2)).split(",");


            br = new BufferedReader(new
                    FileReader(file));


            if ((textstring = br.readLine()) != null) {

                this.obj = textstring;

                this.position = new Position(Integer.parseInt(splitData[0]), Integer.parseInt(splitData[1]));
                this.sensorData = (new ArrayList<>(Arrays.asList(splitData)));
                this.sensorData.add(String.valueOf(new ArrayList<>(Arrays.asList(sd2))));
                this.sensorData.add(String.valueOf(new ArrayList<>(Arrays.asList(sd3))));


				Double degvar =( (float) Math.atan2(Integer.parseInt(splitData[0]), Integer.parseInt(splitData[1]))) * ((180/Math.PI));
				System.out.println(degvar);
				
				
				
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
