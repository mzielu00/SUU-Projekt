Aktualnie pliki gotowe do postawienia klastra są dla central-service (Dockerfile, k8s.yaml)

Poradnik: https://www.fullstackbook.com/devops/how-to-deploy-spring-boot-with-amazon-eks/


# Jak uruchomić u siebie klaster z działającym na nim spring boot serwisem?

## Stworzenie klastra i node grupy

To trzeba zrobić tak jak na labach. Najlepiej przez konsolę AWS, przez CLI pojawiają się problemy z uprawnieniami.

Przy tworzeniu ustawiamy wszystko na default.
Jeśli pojawi się problem z tworzeniem klastra, poprawiamy subnety, pozbywając się tych które nie działają.

W czasie kiedy tworzy się cluster i node grupa można przejść do następnych kroków.

## Stworzenie repo i logowanie

Korzystając z AWS Elastic Container Registry (ECR) tworzymy dwa repozytoria - `edge` oraz `central`, gdzie będziemy przechowywać obrazki serwisów.

Robimy to tak, jak w instrukcji pod linkiem.
Logujemy się do ECR (pierwsza z komend dostępnych pod "View push commands") i przechodzimy do następnego kroku.

## Budowanie projektu paczek i upload na AWS ECR

Stwórz zbudowany serwis w formacie .jar korzystając z mavena, np. tak:

(W głównym folderze serwisu)
```
mvn clean package
```

Odpowiednie `Dockerfile` znajdują się w serwisie `central` oraz `edge`.

Budujemy obrazek:
```
docker build -t <service-name> .
```

Dodatkowo tagujemy obrazek (trzecie z poleceń dostępnych pod "View push commands").

I wrzucamy obrazek do repozytorium (czwarta komenda).

Powtarzamy dla drugiego serwisu.

## Sprawdzamy, czy klaster stoi i jest gites

W następnym kroku będziemy tworzyć w tym clustrze pody, korzystając z wrzuconych obrazków.

```
aws eks describe-cluster --region us-east-1 --name my-cluster --query cluster.status
```

Nie wiem co to robi, ale trzeba puścić bo potem nie zadziała:
```
aws eks --region us-east-1 update-kubeconfig --name my-cluster
```

## Tworzymy i uruchamiamy poda z naszym serwisem

Pod będzie działać w stworzonym clustrze, korzystając z jednego z nodeów w naszej node grupie.

Configurację poda tworzymy w pliku `k8s.yaml`.
Można wzorować się na tej stworzonej dla `central-service`.

W konfiguracji podajemy obrazek Dockerowy, z którego będzie startowany pod.
Musi to być obrazek, który właśnie wrzuciliśmy na EKR.

Aby zdobyć link do obrazka, wchodzimy do naszego repozytorium EKR i przy obrazku klikamy "Copy URI", które wklejamy do configu.
![img.png](assets/aws_ekr_image.png)

W konfiguracji warto zwrócić uwagę na mapowanie portów oraz przydzielone zasoby CPU i Memory.
128 MB to na pewno za mało :p.


Startujemy poda korzystając z konfiguracji:

```
kubectl apply -f k8s.yaml
```

Możemy sprawdzić jego status:
```
kubectl get pod
```
Czekamy, aż status będzie `Running`.

Następnie możemy wylistować serwisy:
```
kubectl get svc
```

Stąd mamy `EXTERNAL-IP` naszego LoadBalancera.
Możemy uderzyć na niego z przeglądarki i zobaczyć "Hello World" (dla central cluster):

```
<<External-IP>>:8250/central/data/
```
