package com.example.practica;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProductoAdapter extends BaseAdapter {
    public ArrayList<Productos> dataProductos;
    public Context context;
    private MainActivity clasePrincipal;

    public ProductoAdapter(ArrayList<Productos> dataProductos, Context context, MainActivity clasePrincipal) {
        this.dataProductos = dataProductos;
        this.context = context;
        this.clasePrincipal = clasePrincipal;
    }

    public ProductoAdapter(ArrayList<Productos> dataProductos, Context context) {
        this.dataProductos = dataProductos;
        this.context = context;
    }
    @Override
    public int getCount() {
        return this.dataProductos.size();
    }

    @Override
    public Object getItem(int i) {
        return this.dataProductos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.item_list,null);
        Productos productoItem = dataProductos.get(i);
        //DEFINIENDO ELEMENTOS
        TextView lblNombreProducto = view.findViewById(R.id.lblNombre);
        TextView lblPrecioProducto = view.findViewById(R.id.lblPrecio);
        TextView lblCantidadProducto = view.findViewById(R.id.lblCantidad);
        TextView lblTotal = view.findViewById(R.id.lblTotal);
        ImageView imgProducto = view.findViewById(R.id.imageView2);
        ImageButton btnEliminar = view.findViewById(R.id.imageButton2);
        ImageButton btnIncrementar = view.findViewById(R.id.btnAumentar);
        ImageButton btnDecrementar = view.findViewById(R.id.btnDecrementar);
        //ASIGNANDO VALORES
        lblNombreProducto.setText(productoItem.getNombreProducto());
        lblPrecioProducto.setText(String.valueOf(productoItem.getPrecio()));
        lblCantidadProducto.setText(String.valueOf(productoItem.getCantidad()));
        lblTotal.setText(String.valueOf(productoItem.getTotal()));
        imgProducto.setImageResource(productoItem.getCategoria().getImagen());
        imgProducto.setColorFilter(context.getResources().getColor(productoItem.getCategoria().getColor()));
        //evento del boton
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialogo = alertaEliminar(i);
                dialogo.show();
            }
        });
        //evento para incrementar
        btnIncrementar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Productos productoItem = dataProductos.get(i);
                int cantidad = productoItem.getCantidad()+1;
                productoItem.setCantidad(cantidad);
                double nuevoTotal = productoItem.getPrecio()*cantidad;
                productoItem.setTotal(nuevoTotal);
                notifyDataSetChanged();
                clasePrincipal.calcularTotalCompra();
            }
        });
        btnDecrementar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Productos productoItem = dataProductos.get(i);
                int cantidad = productoItem.getCantidad()-1;
                if(cantidad ==0){
                    dataProductos.remove(i);
                }
                productoItem.setCantidad(cantidad);
                double nuevoTotal = productoItem.getPrecio()*cantidad;
                productoItem.setTotal(nuevoTotal);
                notifyDataSetChanged();
                clasePrincipal.calcularTotalCompra();
            }
        });
        return view;
    }
    public AlertDialog alertaEliminar(int posicion){
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle("Eliminar")
                .setMessage("Esta Seguro que desea Eliminar el Registro")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dataProductos.remove(posicion);
                        notifyDataSetChanged();
                        clasePrincipal.calcularTotalCompra();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "Registro No eliminado", Toast.LENGTH_SHORT).show();
                    }
                });
        return  builder.create();
    }
}
