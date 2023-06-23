# Todo Uygulaması

Projede, Spring Boot, JPA Hibernate, Spring Security, JWT, MySQL kullanarak bir Todo RESTful api geliştirilmiştir. 

- Kullanıcılar uygulamaya kayıt olabilir, giriş yapabilir ve kendi görevlerini yönetebilir.

-  Her kullanıcı, yeni görevler eklenebilecek, mevcut görevleri güncelleyebilecek ve
   silebilecek.
-  Görevler tamamlandığında kullanıcılar bunu işaretleyebilecek.
 
- Kullanıcılar sadece kendi görevlerini görebilecek.

- Kullanıcılar, tüm görevleri veya tamamlanan görevleri listeleme seçeneklerine
  sahip olacak.


## Kurulum

Aşağıdaki adımları takip ederek uygulamanızı yerel bir geliştirme ortamında çalıştırabilirsiniz.

### Önkoşullar

- Java JDK 20
- Maven
- MySQL veritabanı
- Bir API geliştirme ortamı (örneğin, Postman)

### Adımlar

1. Proje Klonlama: Projenizi GitHub veya herhangi bir kaynak kontrol sistemi kullanarak bir dizine klonlayın.
Eğer projeniz yerel bir dizinde ise, bu adımı atlayabilirsiniz.

2. MySQL veritabanında bir veritabanı oluşturun.

3. Projenin ana dizinindeki `application.properties` dosyasını düzenleyin
ve application.properties'de username ve password ayarlarını veritabanınıza göre güncelleyin:


4. IDE Kurulumu: Bir Java IDE'si (Integrated Development Environment) kullanarak projenizi
geliştirmeniz önerilir. Örneğin, IntelliJ IDEA veya Eclipse gibi popüler IDE'lerden birini
tercih edebilirsiniz. IDE'nizi indirip kurduktan sonra, projenizi IDE üzerinde açabilirsiniz.

5. Bağımlılıkların Yüklenmesi: Bu projede Maven kullanılmaktadır. Bağımlılıkları IDE üzerinden eklemek için aşağıdaki adımları izleyin:

    - IDE'nizi açın ve projenizi yükleyin veya açın.

    - Projeye sağ tıklayın ve "Properties" veya "Proje Ayarları" seçeneğini seçin.

    - Proje ayarları penceresinde, "Dependencies" veya "Bağımlılıklar" bölümünü bulun.

    - Bağımlılıkları eklemek için "Add" veya "Ekle" düğmesini tıklayın.

    - Maven bağımlılıkları aramak ve eklemek için bir pencere açılacaktır. İstediğiniz bağımlılıkları seçin ve "OK" veya "Tamam" düğmesini tıklayın.

    - Seçtiğiniz bağımlılıklar projenize eklenir ve IDE tarafından otomatik olarak indirilir.

    - Bağımlılıkların başarıyla yüklendiğini ve projeye eklendiğini doğrulamak için projenizi derleyin veya yeniden başlatın.

Bu adımları takip ederek, projenizin bağımlılıklarını kolayca ekleyebilir ve geliştirme sürecinizi daha hızlı ilerletebilirsiniz.

7. Uygulama başarıyla başlatıldıktan sonra, API endpoint'lerini test etmek için bir API geliştirme ortamı kullanın (örneğin, Postman).

## API Endpoint'ler

Aşağıdaki API endpoint'leri uygulamada mevcuttur:

- **POST /auth/register**: Kullanıcı kaydı yapar.
- **POST /auth/login**: Kullanıcı girişi yapar ve JWT token döndürür.
- **GET /users**: Tüm kullanıcıları listeler.
- **GET /users/userId**: Id'si verilen kullanıcıyı döndürür.
- **POST /users**: Yeni kullanıcı ekler.
- **PUT /users/userId**: Id'si verilen kullanıcıyı günceller
- **DELETE /users/userId**: Id'si verilen kullanıcıyı siler
- **GET /tasks**: Tüm görevleri listeler.
- **GET /tasks/completed**: Tamamlanan görevleri listeler.
- **PUT /tasks/taskId**: Belirli bir görevi günceller.
- **GET /tasks/taskId**: Belirli bir görevi getirir.
- **POST /tasks**: Yeni bir görev oluşturur.
- **DELETE /tasks/taskId**: Belirli bir görevi siler.
- **PUT /tasks/taskId/complete**: Belirli bir görevi tamamlar.

## Güvenlik

Bu uygulamada JWT (JSON Web Token) kullanılarak güvenli bir kimlik doğrulama sistemi kurulmuştur. Kullanıcı kaydı, giriş yapma ve yetkilendirme işlemleri için JWT token kullanılmalıdır.

JWT token, her API isteği için `Authorization` başlığı altında `Bearer <token>` şeklinde gönderilmelidir.

## Veritabanı Yapısı

Uygulama, MySQL veritabanını kullanmaktadır.
