package nedis.study.jee.security;


import nedis.study.jee.entities.Account;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


public class SecurityUtils {

    public static long getCurrentIdAccount() {
        CurrentAccount a = getCurrentAccount();
        return a != null ? a.getIdAccount() : -1;
    }

    public static CurrentAccount getCurrentAccount() {
        Object principal;
        try {
            principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
        if (principal instanceof CurrentAccount) {
            return (CurrentAccount) principal;
        }
        return null;
    }

    public static void authentificate(Account account, int role) {
        CurrentAccount currentAccount = new CurrentAccount(account);
        currentAccount.setRole(role);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                currentAccount,
                account.getPassword(),
                AuthentificationService.convert(account.getAccountRoles()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
