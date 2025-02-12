Nama: Valentino Kim Fernando
NPM: 2306275771

# Reflection 1
## Prinsip-prinsip Clean Code dan Secure Coding yang diterapkan
Pada projek kali ini, saya telah menerapkan beberapa coding standards Java di antaranya:
- Nama variable yang bermakna
Nama variabel, metode dan kelas menggunakan nama yang deskriptif dan intuitif untuk pembaca.
Misalnya, `Product`, `ProductService, `ProductController` yang menggambarkan dengan jelas peran mereka dalam app.

- Membuat function sependek mungkin
Membuat fungsi yang hanya memiliki satu tujuan saja. Contohnya create, edit, delete dan lain nya yang hanya didedikasikan
untuk memiliki satu tujuan saja.

- Menghindari Rendundansi
`ProductRepository` dengan baik menggunakan metode helper (misalnya `findById`) untuk menghindari kode yang redundant.
Metode edit memanfaatkan `findById` untuk menghindari duplikasi logic pencarian produk.

- Konsisten dalam formating dan indentasi
Hal ini dilakukan supaya coder maupun pembaca bisa dengan nyaman membaca code.

## Hal yang dapat dikembangkan
- Validasi Data
Validasi tentunya penting dalam keamanan sebuah app, tentunya ini akan menambahkan kinerja app ke arah yang lebih positif.

- Penanganan Error dan Feedback ke Pengguna:
Saat ini, tidak ada penanganan jikalau produk tidak ditemukan (misalnya, saat mencoba mengedit atau menghapus produk yang tidak ada).
Menambahkan penanganan error dan feedback kepada pengguna akan meningkatkan user experience dan ketahanan aplikasi.
Sebagai contoh, Anda bisa menampilkan pesan kesalahan atau mengarahkan pengguna ke halaman "produk tidak ditemukan".

# Reflection 2
# Nomor 1
- Setelah saya membuat unit testing, saya menjadi lebih terbuka dengan debugging menggunakan testing, coder tidak harus melaksanakan alur yang panjang untuk mengetahui hasil dari suatu test.
- Seharusnya semakin banyak test yang dibuat, maka semakin baik sebuah app terjaga secara fungsional.
- Dibagi menjadi beberapa kasus-kasus tertentu yang memiliki outcome berbeda, semakin banyak kasus yang di-cover oleh unit test maka semakin baguslah kode tersebut.
- Menurut saya, kode yang memiliki 100% code coverage belum tentu terjamin tidak memiliki bug. Ini lebih memberikan gambaran sejauh mana kode itu diuji. Misal unit testing belum men-cover suatu bug tertentu, maka bisa saja code coverage-nya 100%.

# Nomor 2
- Seharusnya kode nya akan serupa dari segi setup procedures dan instance variables. Hal ini dapat mengurangi kualitas kode karena akan terjadi duplikat, yang tentunya tidak sesuai dengan kaidah clean code yang telah diajarkan pada modul kali ini. Untuk mengembangkan kasus ini, kita bisa membuat setup pada fungsi atau kelas tertentu sehingga bisa dipakai berulang kali tanpa membuat kode mengalami duplikasi.
