package com.cecewash.controller;

import com.cecewash.model.Laundry;
import com.cecewash.repository.LaundryRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/laundry")
public class LaundryController {

    private final LaundryRepository laundryRepository;

    public LaundryController(LaundryRepository laundryRepository) {
        this.laundryRepository = laundryRepository;
    }

    @GetMapping
    public String daftarLaundry(
            @RequestParam(required = false) String keyword,
            Model model
    ) {
        List<Laundry> daftarLaundry;

        if (keyword == null || keyword.isBlank()) {
            daftarLaundry = laundryRepository.findAllByOrderByIdDesc();
        } else {
            daftarLaundry = laundryRepository
                    .findByNamaPelangganContainingIgnoreCaseOrNomorHpContainingOrderByIdDesc(
                            keyword,
                            keyword
                    );
        }

        List<Laundry> semuaData = laundryRepository.findAll();
        long jumlahProses = semuaData.stream()
                .filter(data -> Objects.equals(data.getStatus(), "BARU") || Objects.equals(data.getStatus(), "PROSES"))
                .count();
        long jumlahSelesai = semuaData.stream()
                .filter(data -> Objects.equals(data.getStatus(), "SELESAI") || Objects.equals(data.getStatus(), "DIAMBIL"))
                .count();

        model.addAttribute("daftarLaundry", daftarLaundry);
        model.addAttribute("totalPesanan", semuaData.size());
        model.addAttribute("jumlahProses", jumlahProses);
        model.addAttribute("jumlahSelesai", jumlahSelesai);
        model.addAttribute("keyword", keyword == null ? "" : keyword);

        return "laundry/index";
    }

    @GetMapping("/tambah")
    public String formTambah(Model model) {
        Laundry laundry = new Laundry();
        laundry.setStatus("BARU");

        model.addAttribute("laundry", laundry);
        model.addAttribute("judulForm", "Tambah Pesanan");
        return "laundry/form";
    }

    @PostMapping("/simpan")
    public String simpan(
            @Valid Laundry laundry,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            model.addAttribute("judulForm", "Tambah Pesanan");
            return "laundry/form";
        }

        laundry.setStatus("BARU");
        laundry.setTotalHarga(hitungTotal(laundry.getJenisLayanan(), laundry.getBerat()));
        laundryRepository.save(laundry);

        redirectAttributes.addFlashAttribute("pesan", "Pesanan berhasil ditambahkan");
        return "redirect:/laundry";
    }

    @GetMapping("/edit/{id}")
    public String formEdit(
            @PathVariable Long id,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        Optional<Laundry> data = laundryRepository.findById(id);

        if (data.isEmpty()) {
            redirectAttributes.addFlashAttribute("pesanError", "Data pesanan tidak ditemukan");
            return "redirect:/laundry";
        }

        model.addAttribute("laundry", data.get());
        model.addAttribute("judulForm", "Edit Pesanan");
        return "laundry/form";
    }

    @PostMapping("/update/{id}")
    public String update(
            @PathVariable Long id,
            @Valid Laundry laundry,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            laundry.setId(id);
            model.addAttribute("judulForm", "Edit Pesanan");
            return "laundry/form";
        }

        laundry.setId(id);
        laundry.setTotalHarga(hitungTotal(laundry.getJenisLayanan(), laundry.getBerat()));
        laundryRepository.save(laundry);

        redirectAttributes.addFlashAttribute("pesan", "Pesanan berhasil diperbarui");
        return "redirect:/laundry";
    }

    @PostMapping("/status/{id}")
    public String updateStatus(
            @PathVariable Long id,
            @RequestParam String status,
            RedirectAttributes redirectAttributes
    ) {
        Optional<Laundry> data = laundryRepository.findById(id);

        if (data.isPresent()) {
            Laundry laundry = data.get();
            laundry.setStatus(status);
            laundryRepository.save(laundry);
            redirectAttributes.addFlashAttribute("pesan", "Status berhasil diperbarui");
        }

        return "redirect:/laundry";
    }

    @PostMapping("/hapus/{id}")
    public String hapus(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes
    ) {
        if (laundryRepository.existsById(id)) {
            laundryRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("pesan", "Pesanan berhasil dihapus");
        } else {
            redirectAttributes.addFlashAttribute("pesanError", "Data pesanan tidak ditemukan");
        }

        return "redirect:/laundry";
    }

    private int hitungTotal(String layanan, Double berat) {
        if (berat == null || berat <= 0) {
            return 0;
        }

        int hargaPerKg = 0;
        if ("REGULER".equalsIgnoreCase(layanan)) {
            hargaPerKg = 7000;
        } else if ("SETRIKA".equalsIgnoreCase(layanan)) {
            hargaPerKg = 5000;
        } else if ("KILAT".equalsIgnoreCase(layanan)) {
            hargaPerKg = 12000;
        } else if ("EXPRESS".equalsIgnoreCase(layanan)) {
            hargaPerKg = 20000;
        }

        return (int) Math.round(hargaPerKg * berat);
    }
}
