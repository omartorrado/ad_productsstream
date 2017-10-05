/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productsstream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author otorradomiguez
 */
public class Productsstream {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Product po1=new Product("cod1","parafusos",3);
        Product po2=new Product("cod2","arandelas",4);
        String ruta="/home/local/DANIELCASTELAO/otorradomiguez/NetBeansProjects/productsstream/produtos.txt";
        escribir(ruta,po1,false);
        escribir(ruta,po2,true);
        leer(ruta,0);
        leer(ruta,1);
    }
    /**
     * 
     * @param ruta String indicando la ruta del fichero
     * @param produto Objeto product cuyos datos queremos guardar
     * @param append false para sobreescribir, true para borrar lo que hubiese en el archivo
     */
    public static void escribir(String ruta,Product produto,Boolean append){
        try {
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(ruta, append)));
            dos.writeUTF(produto.getCodigo());
            dos.writeUTF(produto.getDescricion());
            dos.writeDouble(produto.getPrezo());
            dos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Productsstream.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Productsstream.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 
     * @param ruta String indicando la ruta del fichero
     * @param skip numero de objetos que se saltará al leer
     */
    public static Product leer(String ruta,int skip) {
        Product po3=new Product();        
        try {
            DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(ruta)));
            if(skip!=0){
                for(int i=0;i<skip;i++){
                    dis.readUTF();
                    dis.readUTF();
                    dis.readDouble();
                }
            }
            po3.setCodigo(dis.readUTF());
            po3.setDescricion(dis.readUTF());
            po3.setPrezo(dis.readDouble());
            System.out.println("Codigo: "+po3.getCodigo()+", descripcion: "+po3.getDescricion()+", Prezo: "+po3.getPrezo()+"€");
            dis.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Productsstream.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Productsstream.class.getName()).log(Level.SEVERE, null, ex);
        }
        return po3;
    }
    
}
