/*
    Concurrency, ialah beberapa proses yang terjadi secara
    bersamaan dalam suatu sistem. Setiap aplikasi yang
    mempunyai proses yang cukup memakan waktu sebaiknya
    menggunakan concurrancy, proses tersebut seperti
    kompresi video, mengambil data dari server, komputasi,
    chaching, dll.

    Concurrency dan parallelism memiliki arti yang mirip,
    yakni 2 atau lebih proses yang berjalan pada satu waktu.

    Concurrency terjadi apabila terdapat 2 (dua) atau lebih
    proses yang tumpang tindih dalam satu waktu, ini bisa
    terjadi jika ada 2 (dua) atau lebih thread yang sedang
    aktif dijalankan oleh komputer yang hanya memiliki 1 core.
    Sedangkan parallelism terjadi ketika 2 (dua) proses
    dijalankan pada titik waktu yang sama persis. Parallelism
    bisa dilakukan jika terdapat 2 (dua) atau lebih thread serta
    komputer yang juga memiliki 2 (dua) core atau lebih

    intinya parallelism dapat menimbulkan concurrency, tetapi
    concurrency bisa terjadi tanpa parallelism.
*/

/*
    Konsep dasar concurrency

    1. Process
    Proses adalah bagian dari aplikasi yang dijalankan dalam
    sistem operasi, mungkin terdiri dari beberapa thread.
    Meskipun sering dianggap sama dengan aplikasi, sebenarnya
    suatu aplikasi adalah serangkaian proses yang saling bekerja
    sama. Inter Process Communication (IPC) memfasilitasi
    komunikasi antar proses, termasuk antara sistem yang berbeda.

    2. Thread
    Thread merupakan unit eksekusi yang lebih ringan dibandingkan
    proses, memerlukan lebih sedikit sumber daya. Dalam suatu
    proses, setidaknya terdapat satu thread utama, disebut main
    thread, yang menentukan siklus hidup proses. Lebih dari satu
    thread dapat dijalankan secara bersamaan dalam proses yang
    sama, memungkinkan berbagi sumber daya seperti memori dan
    data.

    Thread memiliki penyimpanan lokal sendiri, disebut
    thread-local storage, dan dapat mengakses sumber daya dalam
    proses tetapi juga dapat terblokir. Meskipun hanya satu
    instruksi dalam thread yang dapat dijalankan pada suatu waktu,
    banyak thread dapat dibuat dan berkomunikasi untuk mencegah
    pemblokiran yang dapat mempengaruhi pengalaman pengguna.

    3. I/O-Bound
    I/O-bound, suatu jenis algoritma, terkait erat dengan
    operasi input/output dan dapat menciptakan bottleneck dalam
    aplikasi. Bottleneck atau kemacetan dalam aplikasi merupakan
    hal krusial yang perlu ditangani untuk optimalisasi kinerja.
    Waktu eksekusi I/O-bound bergantung pada kecepatan
    perangkat yang digunakan, seperti membaca dan menulis
    dokumen pada SSD atau HDD.

    Algoritma I/O-bound cenderung menunggu operasi I/O sehingga
    memungkinkan perangkat dengan satu core untuk menjalankan
    tugas lain selama penantian. Konsep ini berlaku baik untuk
    eksekusi paralel maupun dalam satu core. Untuk mengatasi
    ketergantungan I/O, penerapan concurrency disarankan.
*/


/*
    Masalah pada Concurrency

    1. Deadlock
    sebuah kondisi dimana dua proses atau lebih saling menunggu
    proses yang lain untuk melepaskan resource yang sedang
    digunakan, sehingga mengakibatkan proses-proses yang sedang
    berjalan tak kunjung selesai, tak jarang salah satu proses
    dihentikan. Kasus ini umum terjadi ketika banyak proses
    yang saling berbagi sumber daya.

    2. Livelock
    sama dengan deadlock, livelock merupakan kondisi dimana sebuah
    proses tidak bisa melanjutkan tugasnya. namun, yang membedakannya
    adalah, selama livelock terjadi, keadaan dari aplikasi
    tetap bisa berubah, walaupun perubahan keadaan tersebut
    menyebabkan proses berjalan dengan tidak semestinya.

    Livelock bisa terjadi ketika beberapa proses bisa bereaksi
    saat mengalami deadlocks. Proses tersebut mencoba keluar
    dari situasi deadlock, namun waktu yang tidak tepat,
    menghalanginya.

    3. Starvation
    Starvation adalah kondisi yang terjadi setelah deadlock
    dan disebabkan oleh kekurangan sumber daya. Hal ini dapat
    disebabkan oleh kesalahan sistem, ketidakseimbangan
    pembagian sumber daya, atau algoritma penjadwalan yang
    kurang tepat. Contoh kesalahan algoritma penjadwalan dapat
    menyebabkan beberapa tugas tidak pernah dijalankan,
    mengalami starvation. Solusi untuk starvation adalah dengan
    penggunaan algoritma penjadwalan dengan antrian prioritas
    dan penerapan teknik aging, sebuah teknik yang secara
    bertahap meningkatkan prioritas sebuah proses yang menunggu
    dalam sistem pada waktu yang cukup lama.

    4. Race condition
    Race condition terjadi dalam concurrency ketika beberapa
    thread mengakses dan mencoba mengubah data bersama secara
    bersamaan. Hal ini dapat mengakibatkan perilaku sistem yang
    tidak semestinya dan menyebabkan bug sulit diprediksi.
    Race condition sulit direproduksi dan di-debug karena hasil
    akhirnya bergantung pada waktu relatif thread yang
    bersangkutan. Untuk menghindari masalah ini, perencanaan
    sistem yang hati-hati dan pencegahan race condition lebih
    baik daripada memperbaikinya setelah terjadi. Contoh
    sederhana dari race condition dapat ditemukan dalam konteks
    aplikasi perbelanjaan online, di mana stok barang dapat
    berubah tanpa peringatan akibat akses bersamaan dari
    pengguna.

*/

