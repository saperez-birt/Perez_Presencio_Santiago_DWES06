<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Http;

class UsuarioController extends Controller
{
    private $apiBaseUrl;

    public function __construct()
    {
        $this->apiBaseUrl = 'http://localhost:8080/api';
    }

    public function index()
    {
        try {
            $response = Http::get($this->apiBaseUrl . '/usuarios');
            
            if ($response->successful()) {
                return response()->json($response->json());
            }
            
            return back()->with('error', 'Error al obtener los usuarios: ' . $response->status());
        } catch (\Exception $e) {
            return back()->with('error', 'Error de conexión con el microservicio: ' . $e->getMessage());
        }
    }

    public function show($id)
    {
        try {
            $response = Http::get("{$this->apiBaseUrl}/usuarios/{$id}");
            
            if ($response->successful()) {
                return response()->json($response->json());
            }
            
            return response()->json([
                'error' => 'Usuario no encontrado'
            ], 404);
        } catch (\Exception $e) {
            return response()->json([
                'error' => 'Error de conexión con el microservicio',
                'message' => $e->getMessage()
            ], 500);
        }
    }

    public function store(Request $request)
    {
        $request->validate([
            'username' => 'required|string|max:255',
            'nombre' => 'required|string|max:255',
            'apellido' => 'required|string|max:255',
            'email' => 'required|email|max:255',
            'fechaNacimiento' => 'required|string|max:10',
        ]);

        try {
            $response = Http::post($this->apiBaseUrl . '/usuarios', [
                'username' => $request->username,
                'nombre' => $request->nombre,
                'apellido' => $request->apellido,
                'email' => $request->email,
                'fechaNacimiento' => $request->fechaNacimiento,
            ]);
            
            if ($response->successful()) {
                return response()->json($response->json(), 201);
            }
            
            return response()->json([
                'error' => 'Error al crear el usuario',
                'message' => $response->body()
            ], $response->status());
        } catch (\Exception $e) {
            return response()->json([
                'error' => 'Error de conexión con el microservicio',
                'message' => $e->getMessage()
            ], 500);
        }
    }

    public function update(Request $request)
    {
        $request->validate([
            'username' => 'required|string|max:255',
            'nombre' => 'required|string|max:255',
            'apellido' => 'required|string|max:255',
            'email' => 'required|email|max:255',
            'fechaNacimiento' => 'required|string|max:10',
        ]);

        try {
            $response = Http::put("{$this->apiBaseUrl}/updateusuarios", [
                'idUsuario' => $request->idUsuario,
                'username' => $request->username,
                'nombre' => $request->nombre,
                'apellido' => $request->apellido,
                'email' => $request->email,
                'fechaNacimiento' => $request->fechaNacimiento,
            ]);
            
            if ($response->successful()) {
                return response()->json($response->json());
            }
            
            return response()->json([
                'error' => 'Error al actualizar el usuario',
                'message' => $response->body()
            ], $response->status());
        } catch (\Exception $e) {
            return response()->json([
                'error' => 'Error de conexión con el microservicio',
                'message' => $e->getMessage()
            ], 500);
        }
    }

    public function destroy($id)
    {
        try {
            $response = Http::delete("{$this->apiBaseUrl}/usuarios/{$id}");
            
            if ($response->successful()) {
                return response()->json(['message' => 'Usuario eliminado correctamente']);
            }
            
            return response()->json([
                'error' => 'Error al eliminar el usuario',
                'message' => $response->body()
            ], $response->status());
        } catch (\Exception $e) {
            return response()->json([
                'error' => 'Error de conexión con el microservicio',
                'message' => $e->getMessage()
            ], 500);
        }
    }
}
