/* Design a java class for operating the random access files (using =D) */
// Source : http://www.dailyfreecode.com/code/reads-writes-random-access-file-1204.aspx


import java.io.*;

class  RandRW
{
    publicstaticvoid main(String[] args) 
    {
         RandomAccessFile file = null;
         try{
             file = new RandomAccessFile("rand.txt","rw");

             //Writing to the file
             file.writeChar('V');
             file.writeInt(999);
             file.writeDouble(99.99);

             file.seek(0);     //Go to the begining//Reading from the file
             System.out.println(file.readChar());
             System.out.println(file.readInt());
             System.out.println(file.readDouble());

             file.seek(2);  //Go to the Second Item
             System.out.println(file.readInt());

             //Go to the end and append false to the file
             file.seek(file.length());
             file.writeBoolean(true);

             file.seek(4);
             System.out.println(file.readBoolean());
             file.close();
            }catch(Exception e) {}
        }
}
