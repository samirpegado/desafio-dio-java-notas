package dio.desafio.notas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/notas")
public class NotaController {
    @Autowired
    private NotaRepository notaRepository;

    @GetMapping
    public String listarNotas(Model model) {
        List<Nota> notas = notaRepository.findAll();
        model.addAttribute("notas", notas);
        return "lista-notas";
    }

    @GetMapping("/nova")
    public String novaNotaForm(Model model) {
        model.addAttribute("nota", new Nota());
        return "form-nota";
    }

    @PostMapping("/salvar")
    public String salvarNota(@ModelAttribute Nota nota) {
        notaRepository.save(nota);
        return "redirect:/notas";
    }

    @GetMapping("/editar/{id}")
    public String editarNotaForm(@PathVariable Long id, Model model) {
        Nota nota = notaRepository.findById(id).orElse(null);
        model.addAttribute("nota", nota);
        return "form-nota";
    }

    @GetMapping("/excluir/{id}")
    public String excluirNota(@PathVariable Long id) {
        notaRepository.deleteById(id);
        return "redirect:/notas";
    }
}