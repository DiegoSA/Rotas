package com.example.diego.rotas.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.DateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import com.example.diego.rotas.auxiliares.Entrega;
import com.example.diego.rotas.auxiliares.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by diego on 4/13/17.
 */

public class DBController {
    private SQLiteDatabase db;
    private DBHelper banco;
    private ConnectOracle oracle;
    private ResultSet pedidos;

    public DBController(Context context){
        banco = new DBHelper(context);
    }

    public void inserirEntrega(Context context){
        /*try{
            oracle = new ConnectOracle();
        }catch (ClassNotFoundException e){
            Toast.makeText(context, "Erro ao conectar ao banco oracle", Toast.LENGTH_SHORT).show();
        }

        ContentValues values = new ContentValues();

        try {
            pedidos = oracle.getResult();

            while (pedidos.next()) {
                values.put("numeroNota", pedidos.getString("numeroNota"));
                values.put("codigoCliente", pedidos.getString("codigoCliente"));
                values.put("dataFaturamento", pedidos.getString("dataFaturamento"));
                values.put("dataEntrega", pedidos.getString("dataEntrega"));
                values.put("numeroCarregamento", pedidos.getString("numeroCarregamento"));
                values.put("codigoVendedor", pedidos.getString("codigoVendedor"));

                db = banco.getWritableDatabase();

                db.insertOrThrow("PEDIDO", null, values);
            }

        } catch (SQLException e) {
            Toast.makeText(context, "Erro ao consultar pedidos no banco oracle", Toast.LENGTH_SHORT).show();
        }

        values.put("numeroNota", entrega.getNumeroNota());
        values.put("codigoCliente", entrega.getCodigoCliente());
        values.put("dataFaturamento", String.valueOf(entrega.getDataFaturamento()));
        values.put("dataEntrega", String.valueOf(entrega.getDataEntrega()));
        values.put("numeroCarregamento", entrega.getNumeroCarregamento());
        values.put("codigoVendedor", entrega.getCodigoVendedor());*/

        db = banco.getWritableDatabase();

        //db.insertOrThrow("PEDIDO", null, values);

        db.close();
    }

    public String insertUser(Context context, Usuario usuario){
        ContentValues values;
        long result;

        values = new ContentValues();
        values.put("login", usuario.getLogin());
        values.put("senha", usuario.getSenha());
        values.put("tipo", String.valueOf(usuario.getTipo()));

        db = banco.getWritableDatabase();

        result = db.insertOrThrow("USUARIOS", null, values);

        db.close();
        if(result != -1){

            return "Usuario gravado com sucesso";

        }else {

            return "Erro ao inserir Usuário";

        }
    }

    public Cursor listarUsuarios(){

        String[] campos = {"_id", "login", "senha", "tipo"};

        db = banco.getReadableDatabase();

        Cursor cursor = db.query("USUARIOS", campos, null, null, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        db.close();

        return cursor;
    }

    public Cursor listarPedidos(){

        String[] campos = {"_id", "numeroNota", "codigoCliente", "dataFaturamento", "dataEntrega", "numeroCarregamento", "codigoVendedor"};

        db = banco.getReadableDatabase();

        Cursor cursor = db.query("PEDIDO", campos, null, null, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        db.close();

        return cursor;
    }

    public Cursor listarClientes(){

        String[] campos = {"codigo", "razao", "fantasia", "endereco", "numero", "bairro", "cidade", "codigoVendedor"};

        db = banco.getReadableDatabase();

        Cursor cursor = db.query("CLIENTE", campos, null, null, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        db.close();

        return cursor;
    }

    public void removeUser(int id){
        String where = "_id = " + id + ";";

        db = banco.getWritableDatabase();
        db.delete("USUARIOS", where, null);
        db.close();
    }

    public Cursor consultaUsuario(int id){

        String[] campos = {"_id", "login", "senha", "tipo"};

        db = banco.getReadableDatabase();

        Cursor cursor = db.query("USUARIOS", campos, null, null, null, null, null, null);
        cursor.moveToPosition(id);

        /*Usuario usuario = new Usuario();
        usuario.setId(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
        usuario.setLogin(cursor.getString(cursor.getColumnIndexOrThrow("login")));
        usuario.setSenha(cursor.getString(cursor.getColumnIndexOrThrow("senha")));
        if(cursor.getString(cursor.getColumnIndexOrThrow("tipo")).toString().equals('A')){
            usuario.setTipo('A');
        }else if(cursor.getString(cursor.getColumnIndexOrThrow("tipo")).toString().equals('V')) {
            usuario.setTipo('V');
        }else if(cursor.getString(cursor.getColumnIndexOrThrow("tipo")).toString().equals('C')){
            usuario.setTipo('C');
        }else {
            usuario.setTipo('M');
        }*/


        return cursor;
    }

    public void alterarUsuario(Usuario usuario){
        ContentValues values;
        String where = "_id = " + String.valueOf(usuario.getId()) + ";";

        db = banco.getWritableDatabase();

        values = new ContentValues();
        values.put("login", usuario.getLogin());
        values.put("senha", usuario.getSenha());
        values.put("tipo", String.valueOf(usuario.getTipo()));

        db.update("USUARIOS", values, where, null);
        db.close();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void finalizarEntrega(int numeroPedido){
        ContentValues values;
        Entrega entrega = new Entrega();
        String where = "numeroPedido = " + String.valueOf(numeroPedido);

        db = banco.getWritableDatabase();

        values = new ContentValues();
        values.put("_id", entrega.getId());
        values.put("numeroPedido", entrega.getNumeroNota());
        values.put("codigoCliente", entrega.getCodigoCliente());
        values.put("dataFaturamento", String.valueOf(entrega.getDataFaturamento()));

        //pegar a data atual
        String data = DateFormat.getDateInstance().format(new Date());

        values.put("dataEntrega", data);
        values.put("numeroCarregamento", entrega.getNumeroCarregamento());

        db.update("PEDIDO", values, where, null);
        db.close();

    }
}
