# spring-log2slack

## About
- Declare only @Log2Slack annotation. You can receive log message on Slack.

## Required
- JDK 1.8+
- using Spring Framework
- Slack Incoming WebHooks

## How to use it

**1. Register Local Repository, add dependency**
```bash
// git clone
$ git clone https://github.com/LawrenceAhn/spring-log2slack && cd spring-log2slack
$ mvn install
```
```xml
// add pom.xml
<dependency>
    <groupId>com.geekswise</groupId>
    <artifactId>spring-log2slack</artifactId>
    <version>1.0</version>
</dependency>
```

**2. Creating a Configuration File in your project.**
```java
@Configuration
public class Log2SlackConfiguration extends Log2SlackAppConfiguration {

}
```

**3. Slack Incoming WebHooks Setup**
![Image of WebHooks1](/images/1.png)
![Image of WebHooks2](/images/2.png)

**4. Create application.properties or application.yml**
```yaml
# ex) application.properties
slack.web-hook-url={replace your slack webHookUrl}
slack.channel={replace your slack channel}
```

```yaml
# ex) application.yml
slack:
  web-hook-url: {replace slack webHookUrl}
  channel: {replace slack channel}
```

**5. Declare @Log2Slack annotation to method, you can receive log message on Slack.**
```java
@Log2Slack
public List<Member> getMembers() {
    List<Member> blackPink = new ArrayList<>();
    blackPink.add(new Member("지수", "치츄"));
    blackPink.add(new Member("제니", "젠득이"));
    blackPink.add(new Member("로제", "파스타"));
    blackPink.add(new Member("리사", "날라리사, 요리사, 조리사.."));
    return blackPink;
}
```
Nice! you can receive log message on Slack.
![Image of Slack](/images/3.png)

## Example Source
This project is an example project using spring-log2slack.
- [log2slack-example](https://github.com/LawrenceAhn/log2slack-example)

## License
spring-log2slack is MIT License.