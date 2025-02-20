Nama: Valentino Kim Fernando
NPM: 2306275771

<details>
    <summary><h2>Module 1</h2></summary>

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
</details>

<details open>
    <summary><h2>Module 2</h2></summary>

# Reflection 1

## Link Deployment
http://different-phelia-soydoradesu-d15f7223.koyeb.app/

## Code quality issue yang telah diperbaiki
- Menghapus modifier public pada interface ProductService
Sebelumnya, interface ProductService menggunakan modifier public, padahal modifier tersebut tidak diperlukan. Secara default, metode dalam interface bersifat public. Penghapusan modifier ini bertujuan untuk mengurangi redundansi. Setelah menyadari hal ini, saya langsung menghapus modifier public pada interface ProductService.

- Menghapus unused import
Sebelumnya, terdapat penggunaan import all (import *) yang menyebabkan adanya unused import, karena tidak semua yang diimpor digunakan. Setelah mengetahui hal ini, saya mengganti import bintang dengan import secara spesifik untuk setiap kelas yang diperlukan.

## Apakah implementasi sekarang telah sesuai dengan definisi Continuous Integration and Continuous Deployment (CI/CD)?
Alur kerja yang diterapkan saat ini sudah memenuhi definisi dari CI/CD, karena mencakup tahapan-tahapan penting seperti pengolahan kode, pengujian otomatis, review, dan deployment. Github Workflow memegang peranan penting dalam proses ini, yang terdiri dari beberapa bagian berikut:

- `ci.yml` yang menjalankan unit test setiap kali terjadi push atau pull request, sehingga kita dapat memastikan bahwa perubahan kode tidak merusak fungsionalitas yang sudah ada sebelumnya.
- `pmd.yml` yang melakukan review terhadap kode untuk meminimalkan kesalahan dan menjaga kualitas kode secara keseluruhan.
- `scorecard.yml` yang melakukan analisis untuk memastikan keamanan kode tetap terjaga.

Setelah melalui tahapan-tahapan tersebut, perubahan kode akan di-merge ke branch utama seperti main, kemudian secara otomatis akan di-build oleh Koyeb hingga website dapat berjalan dengan lancar. Dengan demikian, seluruh proses CI/CD telah diimplementasikan dengan baik.

## Code Report
![img.png](src/main/resources/static/img.png)
</details>
