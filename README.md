**📸 Photogram - Scalable Instagram-like Backend System**

Welcome to Photogram — a highly scalable and distributed backend system inspired by Instagram’s architecture, built completely in Java Spring Boot.
This project focuses on real-world system design, distributed computing, scalability, and fault tolerance.

**🚀 Architecture Overview**

![Photogram_HLD](https://github.com/user-attachments/assets/ebeedf8d-377c-4019-a971-e76e767f2937)

**🏗️ Core Features**
Kafka Message Queues for asynchronous communication
Redis Caching for low-latency feeds and follower lists
PostgreSQL + Cassandra Hybrid Storage (structured + NoSQL)
MinIO Object Storage for managing media uploads
Cloudflare CDN for accelerated static file delivery
Load Balancer for horizontal scalability
Prometheus + Grafana for Monitoring and Observability
Logging for auditability and system health

**🛠️ Tech Stack**
Backend           -> Java, Spring Boot
Messaging         -> Apache Kafka
Cache             -> Redis
Databases         -> PostgreSQL, Cassandra
Storage           -> MinIO (S3 Compatible)
Monitoring        -> Prometheus, Grafana
CDN               -> Cloudflare
Load Balancing    -> Nginx / Cloud Load Balancer

**📈 System Design Concepts Implemented**
High Availability (HA)
Eventual Consistency via Kafka
Database Sharding Strategies
Cache Invalidation Patterns
CDN-backed static asset distribution
Asynchronous Feed Generation
Scalable User-Follow Graph Model
Monitoring, Alerting, and Logging

**🌟 Future Enhancements**
Full-text search using ElasticSearch (for posts)
Push Notifications service (with Kafka topics)
Multi-region deployments
Auto-scaling with Kubernetes

**🤝 Contributing**
Feel free to fork this repository and contribute!
Any feedback, improvements, or issues are warmly welcome.

**📢 Connect with Me**
LinkedIn: www.linkedin.com/in/nijen-bhut-b68172222 
Twitter: https://x.com/Nijenpatel06

Built with ❤️ by Nijen.

#️⃣ Tags: #Java #SpringBoot #Kafka #Redis #PostgreSQL #DistributedSystems 



