package com.example.practicarecycleview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolder> {
    public ArrayList<Producto> dataProducto;
    public Context context;
    public Button btnDelete;

    public ProductoAdapter(ArrayList<Producto> dataProducto, Context context) {
        this.dataProducto = dataProducto;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(context).inflate(R.layout.item_list, parent,false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoAdapter.ViewHolder holder, int position) {
        holder.binProducto(dataProducto.get(position),this);
    }

    @Override
    public int getItemCount() {
        return dataProducto.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtNombre;
        public TextView txtPrecio;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtPrecio = itemView.findViewById(R.id.txtPrecio);
            btnDelete = itemView.findViewById(R.id.btnEliminar);
        }
        public void binProducto(Producto producto, ProductoAdapter adapter){
            txtNombre.setText(producto.getNombreProducto());
            txtPrecio.setText(String.valueOf(producto.getPrecioProducto()));
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    AlertDialog dialogo = eliminarProducto(position);
                    dialogo.show();
                }
            });
        }
        public AlertDialog eliminarProducto(int position){
            AlertDialog.Builder builder = new AlertDialog.Builder(context)
                    .setTitle("Eliminar")
                    .setMessage("Desea Eliminar el Producto")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dataProducto.remove(position);
                            notifyDataSetChanged();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(context, "Producto No Eliminado", Toast.LENGTH_SHORT).show();
                        }
                    });
            return builder.create();
        }
    }
}
