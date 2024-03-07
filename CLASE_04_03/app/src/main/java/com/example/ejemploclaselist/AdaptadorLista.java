package com.example.ejemploclaselist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
//interfaz Base Adaptar
public class AdaptadorLista extends BaseAdapter {
    public Context context;
    public ArrayList<producto> dataProducto;
    public int contenedor;

    public AdaptadorLista(Context context, ArrayList<producto> dataProducto, int contenedor) {
        this.context = context;
        this.dataProducto = dataProducto;
        this.contenedor = contenedor;
    }

    @Override
    public int getCount() {
        return dataProducto.size();
    }

    @Override
    public Object getItem(int i) {
        return dataProducto.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vista = view;
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        vista = layoutInflater.inflate(R.layout.itemlist,null);
        producto productoItem = dataProducto.get(i);
        TextView txt1 = vista.findViewById(R.id.textView);
        TextView txt2 = vista.findViewById(R.id.textView2);

        txt1.setText(productoItem.getNombre());
        txt2.setText(String.valueOf(productoItem.getPrecio()));
        return vista;
    }
}
