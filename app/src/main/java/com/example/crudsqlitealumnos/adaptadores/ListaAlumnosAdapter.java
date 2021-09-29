package com.example.crudsqlitealumnos.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudsqlitealumnos.R;
import com.example.crudsqlitealumnos.VerActivity;
import com.example.crudsqlitealumnos.entidades.Alumnos;

import java.util.ArrayList;

public class ListaAlumnosAdapter extends RecyclerView.Adapter<ListaAlumnosAdapter.AlumnoViewHolder> {

    ArrayList<Alumnos> listaAlumnos;

    public ListaAlumnosAdapter(ArrayList<Alumnos> listaAlumnos){
        this.listaAlumnos = listaAlumnos;
    }
    @NonNull
    @Override
    public AlumnoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_alumno,null,false);
        return new AlumnoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlumnoViewHolder holder, int position) {
    holder.viewNombres.setText(listaAlumnos.get(position).getNombres());
    holder.viewApellidos.setText(listaAlumnos.get(position).getApellidos());
        holder.viewCodigo.setText(listaAlumnos.get(position).getCodigo());

    }

    @Override
    public int getItemCount() {
        return listaAlumnos.size();
    }

    public class AlumnoViewHolder extends RecyclerView.ViewHolder {
        TextView viewNombres , viewApellidos,viewCodigo;

        public AlumnoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombres = itemView.findViewById(R.id.viewNombres);
            viewApellidos = itemView.findViewById(R.id.viewApellidos);
            viewCodigo = itemView.findViewById(R.id.viewCodigo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);

                    intent.putExtra("ID",listaAlumnos.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
