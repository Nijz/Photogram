<!DOCTYPE html>
<html lang="en">

<body style="font-family: Arial, sans-serif; line-height: 1.6; margin: 20px;">

<h1>📸 Photogram - Scalable Instagram-like Backend System</h1>
<p>Welcome to <strong>Photogram</strong> — a highly scalable and distributed backend system inspired by Instagram’s architecture, built completely in Java Spring Boot.<br>
This project focuses on <strong>real-world system design</strong>, <strong>distributed computing</strong>, <strong>scalability</strong>, and <strong>fault tolerance</strong>.</p>

<hr>

<h2 id="architecture-overview">🏗️ Architecture Overview</h2>
<p>Photogram is engineered for high scalability and fault tolerance, inspired by proven real-world architectures.<br>
Key architectural components include:</p>
<ul>
  <li><strong>Microservices Communication</strong>: Apache Kafka</li>
  <li><strong>Caching Layer</strong>: Redis for feeds and follower lists</li>
  <li><strong>Data Storage</strong>: Hybrid use of PostgreSQL and Cassandra</li>
  <li><strong>Media Storage</strong>: MinIO (S3-compatible object storage)</li>
  <li><strong>Content Delivery</strong>: Cloudflare CDN</li>
  <li><strong>Load Balancing</strong>: Nginx and/or Cloud Load Balancer</li>
  <li><strong>Monitoring and Observability</strong>: Prometheus and Grafana</li>
  <li><strong>Logging</strong>: Centralized system health and audit logs</li>
</ul>

![Photogram_HLD](https://github.com/user-attachments/assets/ebeedf8d-377c-4019-a971-e76e767f2937)

<hr>

<h2 id="core-features">✨ Core Features</h2>
<ul>
  <li><strong>Kafka Message Queues</strong> for asynchronous and resilient communication</li>
  <li><strong>Redis Caching</strong> for low-latency access to feeds and social graphs</li>
  <li><strong>Hybrid Storage Architecture</strong> combining SQL and NoSQL (PostgreSQL + Cassandra)</li>
  <li><strong>MinIO</strong> for scalable media uploads</li>
  <li><strong>Cloudflare CDN</strong> to accelerate media delivery</li>
  <li><strong>Load Balancer</strong> enabling horizontal scalability</li>
  <li><strong>Prometheus + Grafana</strong> for real-time monitoring</li>
  <li><strong>Comprehensive Logging</strong> for audit and troubleshooting</li>
</ul>

<hr>

<h2 id="tech-stack">🛠️ Tech Stack</h2>
<table border="1" cellpadding="8" cellspacing="0">
  <thead>
    <tr>
      <th>Layer</th>
      <th>Technology</th>
    </tr>
  </thead>
  <tbody>
    <tr><td>Backend</td><td>Java, Spring Boot</td></tr>
    <tr><td>Messaging</td><td>Apache Kafka</td></tr>
    <tr><td>Caching</td><td>Redis</td></tr>
    <tr><td>Relational DB</td><td>PostgreSQL</td></tr>
    <tr><td>NoSQL DB</td><td>Cassandra</td></tr>
    <tr><td>Media Storage</td><td>MinIO (S3-compatible)</td></tr>
    <tr><td>CDN</td><td>Cloudflare</td></tr>
    <tr><td>Load Balancing</td><td>Nginx / Cloud Load Balancer</td></tr>
    <tr><td>Monitoring</td><td>Prometheus, Grafana</td></tr>
  </tbody>
</table>

<hr>

<h2 id="system-design-concepts-implemented">📈 System Design Concepts Implemented</h2>
<ul>
  <li>High Availability (HA) deployments</li>
  <li>Eventual Consistency via Kafka messaging</li>
  <li>Database Sharding for horizontal data partitioning</li>
  <li>Cache Invalidation patterns to ensure data freshness</li>
  <li>CDN Integration for static content delivery</li>
  <li>Asynchronous Feed Generation mechanisms</li>
  <li>Scalable User-Follow Graph Models</li>
  <li>Monitoring, Alerting, and Centralized Logging</li>
</ul>

<hr>

<h2 id="usage">🚀 Usage</h2>
<ul>
  <li>Create a user</li>
  <li>Upload a photo/video post</li>
  <li>Follow/unfollow users</li>
  <li>Retrieve personalized feeds</li>
  <li>Monitor system metrics via Grafana dashboard</li>
</ul>

<hr>

<h2 id="configuration">⚙️ Configuration</h2>
<p>Edit the <code>application.properties</code> or <code>application.yml</code> files to configure:</p>
<ul>
  <li>Database URLs</li>
  <li>Redis settings</li>
  <li>Kafka broker addresses</li>
  <li>MinIO credentials</li>
  <li>Monitoring endpoints</li>
</ul>

<hr>

<h2 id="future-enhancements">🌟 Future Enhancements</h2>
<ul>
  <li>Full-text search with <strong>Elasticsearch</strong></li>
  <li>Push Notifications service (Kafka topics)</li>
  <li>Multi-region deployments</li>
  <li>Auto-scaling with Kubernetes (K8s)</li>
</ul>

<hr>

<h2 id="contributing">🤝 Contributing</h2>
<p>Contributions are warmly welcome!<br>
Feel free to fork the repository and submit Pull Requests.<br>
For significant changes, please open an Issue first to discuss what you would like to change.</p>

<hr>

<h2 id="connect-with-me">📢 Connect with Me</h2>
<ul>
  <li><strong>LinkedIn</strong>: <a href="https://www.linkedin.com/in/nijen-bhut-b68172222">Nijen Bhut</a></li>
  <li><strong>Twitter</strong>: <a href="https://x.com/Nijenpatel06">@Nijenpatel06</a></li>
</ul>

<hr>

<p>#️⃣ Tags: <code>#Java</code> <code>#SpringBoot</code> <code>#Kafka</code> <code>#Redis</code> <code>#PostgreSQL</code> <code>#DistributedSystems</code></p>

</body>
</html>
