import jenkins.model.Jenkins

def j = Jenkins.get()
def realm = j.getSecurityRealm()

if (realm instanceof hudson.security.HudsonPrivateSecurityRealm) {
    def user = hudson.model.User.getById("admin", false)

    if (user == null) {
        realm.createAccount("admin", "Admin@123")
    } else {
        user.updateByXml(new java.io.StringReader(user.getConfigFile().asString()))
    }

    println("Admin account processed")
} else {
    println("Current security realm: " + realm.getClass().getName())
}
