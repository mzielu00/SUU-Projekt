# Smart City

(KCP-21) PoC with the Edge computing aspects and hierarchical dependencies

Authors: 

- Konrad Czerepak
- Zofia Grodecka
- Przemysław Rożnawski
- Maciej Zieliński

Year: 2023 \
Group: 4

## Table of content

[1. Introduction](#introduction) \
[2. Theoretical background and technology stack](#theoretical-background-and-technology-stack) \
[3. Case study concept description ](#case-study-concept-description) \
[4. Solution architecture](#solution-architecture) \
[5. Environment configuration description](#environment-configuration-description) \
[6. Installation method](#installation-method) \
[7. How to reproduce](#how-to-reproduce) \
&ensp; [7.1 Infrastructure as Code approach](#infrastructure-as-code-approach) \
[8. Demo deployment steps](#demo-deployment-steps) \
&ensp; [8.1 Configuration set-up](#configuration-set-up) \
&ensp; [8.2 Data preparation](#data-preparation) \
&ensp; [8.3 Execution procedure](#execution-procedure) \
&ensp; [8.4 Results presentation](#results-presentation) \
[9. Summary](#summary) \
[10. References](#references) 

<!-- toc -->

## Introduction

Kubernetes is a powerful and popular open-source container orchestration platform that has gained significant traction in recent years due to its ability to simplify the deployment and management of containerized applications. However, as the adoption of Kubernetes continues to grow, so do the challenges associated with managing and securing Kubernetes clusters at scale.

One of the emerging needs in the Kubernetes ecosystem is for Kubernetes Control Plane (KCP) solutions. KCP solutions provide an additional layer of security and manageability to Kubernetes clusters by enabling administrators to monitor, audit, and secure the Kubernetes control plane components. This is critical because the control plane components of Kubernetes are responsible for managing the entire cluster and any vulnerabilities or breaches can lead to significant consequences.

KCP solutions offer several benefits, including enhanced visibility into the Kubernetes control plane, increased security, simplified management, and improved compliance. As more organizations adopt Kubernetes, KCP solutions are becoming essential for enterprises to manage and secure their Kubernetes clusters effectively.

In this context, this emerging need for KCP Kubernetes is driving the development of innovative solutions and tools that help enterprises ensure the stability and reliability of their Kubernetes deployments while maintaining security and compliance.

## Theoretical background and technology stack

Containerization is a technology that enables developers to package applications and their dependencies into a single container that can run consistently across different environments. This makes it easier to deploy and manage applications, as they can be deployed as isolated units and easily moved between environments.

Kubernetes is an open-source container orchestration platform that provides a consistent and uniform platform for deploying and managing containerized applications. Kubernetes provides features such as automatic scaling, self-healing, and rolling updates, making it an ideal platform for deploying and managing complex applications.

Kubernetes Control Plane (KCP) is a control plane that is similar to Kubernetes but with a focus on enabling the creation of many independent, isolated "clusters" called workspaces. KCP provides a centralized platform for offering APIs that can be consumed easily by users in their workspaces, and enables flexible scheduling of workloads to physical clusters and transparent movement of workloads among compatible physical clusters.

In terms of the technology stack for this project, we will be using containerization technologies such as Docker and Kubernetes to package and deploy the different components of the system. We will also be using KCP to manage the Kubernetes control plane components, and to enable fine-grained access controls and network segmentation to ensure the security of the system.

For the microservices that handle city data, we will be using Java as the programming language. Java is a popular programming language for building microservices due to its platform independence, robustness, and scalability. We may also use other technologies such as Spring Boot or MicroProfile to facilitate the development of the microservices.

## Case study concept description 

In this case study, we will explore how Kubernetes Control Plane (KCP) can be used to manage a system that mimics a smart city. Specifically, we will focus on how the system can be designed to run on different underlying hardware architectures, while also ensuring the security of the entire system.

The smart city system is designed to collect data from various sensors placed around the city. The system follows a distributed architecture where edge clusters are responsible for collecting and storing data from sensors within their respective areas. These edge clusters aggregate the collected data and forward it to a central server for processing and analysis. The central cluster is responsible for collecting data from the entire city and making it available to users, enabling quick access to data from a selected location. This approach allows for efficient data management and reduces the load on the central server and by utilizing edge servers, the system can minimize latency and ensure quick operations without the need to transfer data to the main server, ultimately improving efficiency and responsiveness.

The sensor capabilities include:

- Air Pollution Monitoring: The smart city system utilizes pollution sensors to measure air quality in various locations throughout the city. These sensors continuously monitor the levels of pollutants in the air, such as particulate matter, nitrogen dioxide, and ozone. 

- Traffic Management: The pollution sensors can also be used to manage traffic in the city. These sensors collect data on traffic flow, congestion levels, and road occupancy. By analyzing this real-time data, the system can identify areas with heavy traffic and adjust the traffic flow accordingly.

- Alerting Citizens: The smart city system is equipped with sensors that enable rapid alerting of citizens in urgent situations. These sensors can detect critical events such as fires, natural disasters, or security breaches. When such events occur, the system immediately triggers alerts to notify citizens and provide them with relevant information and instructions to ensure their safety.

- Self-driving Cars: The smart city system incorporates sensors that detect the proximity of objects around self-driving cars, including other vehicles, bicycles, and pedestrians. These sensors continuously monitor the environment, allowing the system to quickly assess potential dangers and react accordingly.

- Parking Space Updates: The system can offer real-time updates on available parking spaces near the sensors. By monitoring parking spaces in proximity to the sensors, the system can inform drivers about the availability of free parking spaces. This feature optimizes traffic flow by reducing the time spent searching for parking spots.

We will use containerization technologies, such as Docker and Kubernetes, to package and deploy the different components of the system. The container images will be designed to be architecture-agnostic, allowing them to run on different hardware architectures.

Kubernetes will be used to manage the different components of the system, such as the edge clusters and the central server. Kubernetes provides a consistent and uniform platform for managing the different components, regardless of the underlying hardware architecture.

With KCP, we will be able to monitor and manage the Kubernetes control plane components, ensuring that the system operates at optimal performance levels. KCP will also enable fine-grained access controls and network segmentation to ensure the security of the system.

Overall, this case study demonstrates how Kubernetes Control Plane (KCP) solutions can effectively manage a smart city system running on different underlying hardware architectures while prioritizing security. By leveraging containerization technologies, Kubernetes, and KCP, the system abstracts away the complexities of the underlying hardware, providing a consistent and uniform platform for deploying and managing the system.

Furthermore, by utilizing edge servers, the system minimizes latency and ensures quick operations without the need to transfer data to the main server. This optimization enables faster execution of tasks such as alerting citizens about urgent situations, providing real-time updates on parking availability, dynamically managing traffic flow, and assisting self-driving cars. The utilization of edge servers enhances the overall responsiveness of the system, improving efficiency and user experiences.

## Solution architecture

In order to carry out the Demo, we plan to simulate the air quality control system presented earlier using Kubernetes clusters with the help of Kubernetes Control Plane.

We anticipate a hierarchical cluster architecture, where the main service providing data to users has access to all services running on edge clusters in order to access their data.

![plot](./photos/cluster_architecture.png)

By using KCP, communication between clusters will be done through a simple API.

Each of the clusters will have a main service (central cluster) and edge services (edge clusters) that will be created using Java and Spring. They will be simple services designed to simulate the operation of a real smart city for the purpose of demonstrating the use of KCP.

## Environment configuration description

To create a Demo application using Java Spring and Kubernetes KCP, several tools need to be installed and configured on the local machine. 

The main tools required for the application are the Kubernetes command-line interface, called kubectl, and the Kubernetes control plane (KCP) tool. Kubectl is a command-line tool used to deploy and manage applications on a Kubernetes cluster. It provides a simple and intuitive way to interact with the Kubernetes cluster and manage the whole application. KCP is a tool used to manage the Kubernetes control plane components, such as the API server, or scheduler. It provides a centralized view of the Kubernetes control plane and enables a user to manage and monitor the control plane components. In addition to kubectl and KCP, it is required to have a local Kubernetes cluster to deploy and manage microservices. In this case, we will use Minikube, which is a tool that allows us to run a single-node Kubernetes cluster on our local machine. Minikube is easy to set up and provides a lightweight and portable way to test microservices locally. To package our microservices and dependencies into a container image, we will need to use Docker, which is a popular containerization platform that enables users to package their applications and dependencies into a container image. Finally, to develop and implement our proper microservices using Java and SpringBoot, we will need the Java Development Kit (JDK) to compile the Java code, and Apache Maven to manage dependencies and build the application.

By installing and configuring all of these tools on your local machine, you can create and deploy a microservice application using Java Spring and Kubernetes KCP.

## Installation method

To set up the required tools for your Smart City project, you can follow the installation steps outlined below. These steps will guide you through installing Kubernetes, Kubernetes Control Plane (KCP), and Cluster Kind, which are essential components for deploying and managing your microservices application.

1. Kubernetes:

It is essencial to follow the the guidelines provided in the official documentation at https://kubernetes.io/docs/setup/ to install Kubernetes and its command    line tool - kubectl.

2. Kubernetes Control Plane (KCP):

To install KCP one should also stick to the available KCP documentation: https://docs.kcp.io/kcp/main/ and follow the instructions provided in the documentation to set up and configure KCP on your Kubernetes cluster.

3. Cluster Kind:

Kind (Kubernetes IN Docker) is a tool that allows you to run local Kubernetes clusters using Docker containers. It provides an easy and lightweight way to create and manage Kubernetes clusters for testing and development purposes. You can find a quick-start guide on installing and setting up a Kind Cluster on your local machine at https://kind.sigs.k8s.io/docs/user/quick-start/.

By following the installation guides provided in the respective documentation links, you will be able to install Kubernetes, KCP, and Cluster Kind, which are crucial for developing and deploying your Smart City application. These tools will enable you to manage and secure your microservices effectively, ensuring a robust and efficient system.

Please note that the provided links are subject to change. It is recommended to visit the official documentation websites for the most up-to-date installation instructions and guidelines for each tool.

## How to reproduce

### Infrastructure as Code approach

In our microservices Java Spring project, we have adopted the Infrastructure as Code approach to manage our infrastructure and deployments efficiently. We have divided our code into different modules, each representing a distinct service such as the central service and edge service which can be replicated for different city area. To facilitate testing and development, we have created mocks for pollution and traffic data, which we use when retrieving data from specific endpoints.

![img_2.png](img_2.png)

The central service, implemented in the CentralController class, handles requests and acts as a gateway to the other services. For example it communicates with the edge1 service using the Edge1Connector interface, which is a Feign client. This interface defines the endpoints and methods to retrieve average traffic congestion and air pollution data from edge1.

![img.png](img.png)

For deployment, we leverage the Spring Cloud Kubernetes framework and AWS services. The central module is deployed as a Kubernetes Deployment using the provided deployment YAML file. This file specifies the container image, resource limits, and the exposed port. Additionally, we configure a Kubernetes Service to expose the central service externally using a LoadBalancer type.

![img_3.png](img_3.png)

Similarly, every edge module follows a similar pattern. For example in the edge1 module, the Edge1Controller class handles requests related to traffic and air pollution data in the edge1 service. 

![img_4.png](img_4.png)

The deployment of the edge1 service also uses a Kubernetes Deployment and Service, with similar specifications as the central service and every other service.

![img_7.png](img_7.png)

To ensure dynamic configuration and manage properties, we utilize a ConfigMap in Kubernetes. The ConfigMap named 'central' holds application properties for the central module, including the IP addresses of edge1 and edge2 services. This allows us to easily update and manage the configurations without modifying the code.

![img_5.png](img_5.png)

## Demo deployment steps

### Configuration set-up

To set up the configuration for deployment, you will need the following:

- A local development environment with Maven installed.
- An AWS account with the necessary permissions to create resources such as a cluster and node group.

### Data preparation

The application already includes predefined mocks for pollution and traffic data. These mocks will be utilized during the execution of specific endpoints to simulate real-time data processing. No additional steps are required for data preparation in this case.

### Execution procedure

To deploy all services connected together you have to deploy several pods, starting up with edge services, then when they are up you can provide their external-IP adresses into `configMap.yaml` file.

####Deployment of single service:

1. Build the project: Open a command prompt or terminal in the root folder of the service and execute the following command to build the project using Maven:
    ```
    mvn clean package
    ```

2. Build a Docker image (In every module there is a `Dockerfile`)
    ```
    docker build -t <service-name> .
    ```
 

3. Push the Docker image to AWS Elastic Container Registry (ECR): Follow the instructions provided by AWS to set up an Elastic Container Registry (ECR) and push Docker image to ECR:
- Go to Amazon ECR.
- Create a repository.
- Click on "View push commands".
- Follow the instructions to push image.
- Copy the image URI.
- Add image URI to k8s deploy file for intance: `central-pod-config.yaml` 


4. Check the cluster status: Use the AWS CLI to check the status of the cluster you have created. Run the following command:
    ```
    aws eks describe-cluster --region us-east-1 --name <your cluster> --query cluster.status
    ```
   Additionally, update the kubeconfig by running the following command:
    ```
    aws eks --region us-east-1 update-kubeconfig --name <your cluster>
    ```

5. Create and run a pod: Write a Kubernetes configuration file (`file_name.yaml`) for your service or use provided one. Take inspiration from the example configuration provided earlier for the `central-service` and modify it to match your service's requirements. Ensure that you specify the correct Docker image URI from the ECR. Apply the configuration using the following command:
    ```
    kubectl apply -f <file_name.yaml>
    ```

6. Verify the pod status: Check the status of the pod by running the following command:
    ```
    kubectl get pod
    ```
   Wait until the status shows as `Running`.

7. Access the deployed service: List the services using the following command to find the `EXTERNAL-IP` of the LoadBalancer:
    ```
    kubectl get svc
    ```
   Once you have the `EXTERNAL-IP`, access the service from a browser using the provided IP address and port number. For example, for the central cluster:
    ```
    <<External-IP>>:8250/central/data/
    ```

   In addition to accessing the deployed service, you can also retrieve mock data from external URLs by using specific endpoints. Modify the endpoint URLs in your service to fetch the desired mock data during the results presentation phase.

Repeat the above execution procedure for each service in your application, following the specific configurations and requirements for each service.

### Results presentation

After accessing each deployed service using the provided IP address and port number, analyze and present the results as required for your project documentation. This may include retrieving data from the mocked URLs using specific endpoints and showcasing the obtained data or any other processed information from the deployed services.


## Summary

In conclusion, this project provided us with valuable insights and knowledge about Kubernetes Control Plane (KCP) solutions and their potential applications in managing smart city systems. While our initial aspirations were to showcase a fully functional system, we encountered difficulties in successfully running KCP and were unable to achieve our original goals. We deeply regret the challenges we faced and acknowledge our inability to implement KCP as planned, which led us to adapt the project to a more manageable problem.

Throughout the project, we dedicated significant effort to understanding containerization, Kubernetes, and the intricacies of managing complex distributed systems. By leveraging Spring microservices with the Spring Kubernetes Cluster framework, we were able to design and deploy resilient and scalable services.

We relied on the AWS platform for hosting and deploying our system. AWS provided us with reliable infrastructure and a comprehensive range of services, including Amazon EC2 for container execution and Amazon S3 for data storage.

Although we were unable to demonstrate a fully operational system, we are proud of the knowledge we gained and the progress we made. This project served as a valuable learning experience, highlighting the challenges and complexities associated with managing and securing Kubernetes clusters at scale.

We sincerely apologize for not meeting our initial objectives and for the need to modify our project idea to tackle a more manageable problem. We recognize the importance of reflecting on our limitations and failures as an essential part of the learning process.

Looking ahead, despite the challenges we faced in implementing KCP for this project, we are determined to build upon the knowledge we acquired and  aspire to undertake a dedicated project focused on Kubernetes Control Plane (KCP), aiming to fulfill our original goal of effectively managing and securing Kubernetes clusters.

## References
