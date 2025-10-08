

@RestController
@RequestMapping("/api/medicos")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MedicoController {

    private final MedicoService medicoService;

    @GetMapping
    public List<MedicoDTO> listarMedicos() {
        return medicoService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<MedicoDTO> crearMedico(@RequestBody MedicoDTO dto) {
        MedicoDTO creado = medicoService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoDTO> actualizarMedico(@PathVariable Long id, @RequestBody MedicoDTO dto) {
        MedicoDTO actualizado = medicoService.actualizar(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMedico(@PathVariable Long id) {
        medicoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}