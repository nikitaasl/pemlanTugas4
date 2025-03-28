
class Pelanggan{
    private final String nomorPelanggan;
    private double saldo;
    private final String nama;
    private final String pin;

    public Pelanggan(String nomorPelanggan, double saldo, String nama, String pin) {
        this.nomorPelanggan = nomorPelanggan;
        this.saldo = saldo;
        this.nama = nama;
        this.pin = pin;
    }
    public String getNomorPelanggan(){
        return nomorPelanggan;
    }
    public String getNama(){
        return nama;
    }
    public boolean verifikasiPin(String pin) {
        return this.pin.equals(pin);
    }
    public boolean lakukanPembelian(double jumlah) {
        if (jumlah <= 0) {
            System.out.println("Jumlah pembelian tidak valid.");
            return false;
        }
        double cashback = hitungCashback(jumlah);
        double totalSetelahCashback = saldo - jumlah + cashback;

        if(totalSetelahCashback < 10000) {
            System.out.println("Transaksi gagal! Saldo tidak mencukupi setelah pembelian.");
            return false;
        }
        saldo = totalSetelahCashback;
        System.out.println("Pembelian berhasil! Cashback: Rp" + cashback);
        return true;
    }

    private double hitungCashback(double jumlah){
        if(nomorPelanggan.length() < 2) {
            return 0; //Jika nomor pelanggan tidak valid, maka cashabck = 0
        }

        String kodeJenis = nomorPelanggan.substring(0, 2);
        double cashback = 0;

        if (jumlah > 1000000) { //Hanya transaksi diatas 1 juta yang mendapat cashback
            switch (kodeJenis){
                case "38" -> cashback = jumlah * 0.05; //Cashback pelanggan jenis silver
                case "56" -> cashback = jumlah * 0.07; //Cashback pelanggan jenis gold
                case "74" -> cashback = jumlah * 0.10; //Cashback pelanggan jenis platinum
            }
        }else{ //Jika transaksi dibawah 1 juta
            switch(kodeJenis){
                case "56"-> cashback = jumlah * 0.02; //2& cashback kembali ke saldo untuk pelanggan jenis gold
                case "74"-> cashback = jumlah * 0.05; //5% cashback kembali ke saldo untuk pelanggan jenis platinum
                //Pelanggan jenis silver tidak mendapat cashback apabila pembelian dibawah 1 juta (default 0.0)
            }
        }
        return cashback;
    }

    public boolean topUp(double jumlah){
        if (jumlah <= 0) {
            System.out.println("Jumlah top-up tidak valid.");
            return false;
        }
        saldo += jumlah;
        System.out.println("Top-up berhasil! Saldo saat ini: Rp" + saldo);
        return true;
    }
}