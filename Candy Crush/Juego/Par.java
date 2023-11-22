package Juego;

import java.io.Serializable;

public class Par<K,V> implements Serializable{
        //Atributos
        protected K clave;
        protected V valor;

        //Constructor
        public Par(K clave, V valor){
            this.clave = clave;
            this.valor = valor;
        }

        //Metodos
        public K getClave(){ return clave;}
        public V getValor(){ return valor; }
        public void setClave(K clave){ this.clave = clave; }
        public void setValor(V valor){ this.valor = valor; }
    }

