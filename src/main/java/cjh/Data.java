package cjh;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Data {


    public int movement;
    public Object obj;
    Image image;
    Position position;
    private ArrayList<String> sensorData = new ArrayList<>();
    private int[] Vector; // get Vector from two var(integer, by file input stream)

    public int mod;

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
        String[] splitData;
        ArrayList<String> sensor;

        try {


            this.obj = new String(Files.readAllBytes(Paths.get(tempData.path)));
            splitData = (String.valueOf(this.obj)).split(",");
            sensor = new ArrayList<>(Arrays.asList(splitData));

            BufferedReader br = new BufferedReader(new
                    FileReader(file));

            String textstring;
            if ((textstring = br.readLine()) != null) {

                this.obj = textstring;

                this.position = new Position(Integer.parseInt(splitData[0]), Integer.parseInt(splitData[1]));
                this.sensorData = (new ArrayList<>(Arrays.asList(splitData)));



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

        System.out.println(this.getMovement());
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