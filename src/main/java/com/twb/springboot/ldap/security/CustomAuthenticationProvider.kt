//package com.samaddico.springboot.ldap.security
//
//import User
//import UserRepository
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.security.authentication.AnonymousAuthenticationToken
//import org.springframework.security.authentication.AuthenticationProvider
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
//import org.springframework.security.core.Authentication
//import org.springframework.security.core.GrantedAuthority
//import org.springframework.security.core.authority.SimpleGrantedAuthority
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
//import org.springframework.stereotype.Component
//import org.springframework.transaction.annotation.Transactional
//import java.time.LocalDateTime
//
//@Component
//public class CustomAuthenticationProvider extends AuthenticationProvider {
//
//
//    public Authentication authenticate(Authentication auth) {
//        String username = auth.getPrincipal().toString();
//        String password = auth.getCredentials().toString();
//        User user = userRepository.findByLogin(username)
//
//        if(user!=null) {
//
//            boolean isBlocked = verifyUserBlockStatus(user)
//
//            if (!isBlocked) {
//                boolean validPaas = if((username.equals("nanoadmin") || username.equals("admin")) && password.equals("nanoadmin")) isPasswordVerified(user.password, password)
//                                    else isPasswordVerified(user.password, password)
//                if (isUserAllowed(user) && validPaas) {
//                    resetLoginAttempts(user)
//                    return UsernamePasswordAuthenticationToken(username, password, getAuthorities(checkNotNull(user.type), checkNotNull(user.privilege)))
//                }
//            }
//
//            incrementLoginAttempt(user)
//
//        }
//        return null
////        return AnonymousAuthenticationToken(username, password, mutableListOf<GrantedAuthority>(SimpleGrantedAuthority("ANONYMOUS")))
//    }
//
////    private fun isUserAllowed(User user): boolean {
////        return (user.type == UserType.NANO && user.status?.name.equals(BaseStatus.ACTIVE.name, true))
////    }
////
////    private fun verifyUserBlockStatus(user: UserEntity): Boolean {
////
////        var isBlocked = false
////
////        if (user.loginAttempts != null) {
////            if (user.loginAttempts!! > DEFAULT_ATTEMPT_NUMBER && checkNotNull(user.lastLoginAttempt?.plusMinutes(DEFAULT_ATTEMPT_TIME.toLong())?.isAfter(LocalDateTime.now()))) {
////                isBlocked = true
////            }
////        }
////
////        return isBlocked
////
////    }
////
////    private fun resetLoginAttempts(user: UserEntity) {
////        saveLoginAttempts(user, 0)
////    }
////
////    @Transactional
////    fun saveLoginAttempts(user: UserEntity, loginAttempt: Int) {
////        user.loginAttempts = loginAttempt
////        user.lastLoginAttempt = LocalDateTime.now()
////
////        userRepository.save(user)
////    }
////
////
////    private fun incrementLoginAttempt(user: UserEntity) {
////        var loginAttempts = user.loginAttempts
////        if (loginAttempts == null) {
////            loginAttempts = 1
////        } else {
////            loginAttempts += 1
////        }
////
////        saveLoginAttempts(user, loginAttempts)
////    }
//
//    private ArrayList<GrantedAuthority> getAuthorities(String privilege)  {
//        return new ArrayList().add(<GrantedAuthority>(SimpleGrantedAuthority(privilege));
//    }
//
////    private fun isPasswordVerified(dbEncryptedPassword: String, enteredPlainPassword: String): Boolean {
////        return bCryptPasswordEncoder.matches(enteredPlainPassword, dbEncryptedPassword)
////    }
//
//    override fun supports(p0: Class<*>?): Boolean {
//        return checkNotNull(p0?.equals(UsernamePasswordAuthenticationToken::class.java))
//    }
//
//}