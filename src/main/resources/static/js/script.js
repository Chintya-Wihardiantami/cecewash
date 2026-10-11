function confirmDelete() {
    return confirm("Yakin ingin menghapus pesanan ini?");
}

function hitungPreview() {
    const layanan = document.getElementById("jenisLayanan");
    const berat = document.getElementById("berat");
    const preview = document.getElementById("previewTotal");

    if (!layanan || !berat || !preview) {
        return;
    }

    const option = layanan.options[layanan.selectedIndex];
    const harga = Number(option.dataset.harga || 0);
    const jumlahBerat = Number(berat.value || 0);
    const total = Math.round(harga * jumlahBerat);

    preview.textContent = "Rp " + total.toLocaleString("id-ID");
}

document.addEventListener("DOMContentLoaded", hitungPreview);
