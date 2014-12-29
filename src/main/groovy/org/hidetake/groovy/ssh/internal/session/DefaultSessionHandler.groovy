package org.hidetake.groovy.ssh.internal.session

import groovy.util.logging.Slf4j
import org.hidetake.groovy.ssh.api.OperationSettings
import org.hidetake.groovy.ssh.api.Remote
import org.hidetake.groovy.ssh.internal.operation.Operations
import org.hidetake.groovy.ssh.api.session.SessionHandler

/**
 * A default implementation of {@link SessionHandler}.
 *
 * @author hidetake.org
 */
@Slf4j
class DefaultSessionHandler implements SessionHandler {
    final Operations operations

    private final OperationSettings operationSettings

    def DefaultSessionHandler(Operations operations1, OperationSettings operationSettings1) {
        operations = operations1
        operationSettings = operationSettings1
    }

    @Override
    Remote getRemote() {
        operations.remote
    }

    @Override
    void shell(HashMap settings) {
        assert settings != null, 'settings must not be null'
        log.info("Execute a shell with settings ($settings)")
        operations.shell(operationSettings + new OperationSettings(settings))
    }

    @Override
    String execute(String command) {
        assert command, 'command must be given'
        log.info("Execute a command ($command)")
        operations.execute(operationSettings, command, null)
    }

    @Override
    void execute(String command, Closure callback) {
        assert command, 'command must be given'
        assert callback, 'callback must be given'
        log.info("Execute a command ($command) with callback")
        operations.execute(operationSettings, command, callback)
    }

    @Override
    String execute(HashMap settings, String command) {
        assert command, 'command must be given'
        assert settings != null, 'settings must not be null'
        log.info("Execute a command ($command) with settings ($settings)")
        operations.execute(operationSettings + new OperationSettings(settings), command, null)
    }

    @Override
    void execute(HashMap settings, String command, Closure callback) {
        assert command, 'command must be given'
        assert callback, 'callback must be given'
        assert settings != null, 'settings must not be null'
        log.info("Execute a command ($command) with settings ($settings) and callback")
        operations.execute(operationSettings + new OperationSettings(settings), command, callback)
    }

    @Override
    void executeBackground(String command) {
        assert command, 'command must be given'
        log.info("Execute a command ($command) in background")
        operations.executeBackground(operationSettings, command, null)
    }

    @Override
    void executeBackground(String command, Closure callback) {
        assert command, 'command must be given'
        assert callback, 'callback must be given'
        log.info("Execute a command ($command) with callback in background")
        operations.executeBackground(operationSettings, command, callback)
    }

    @Override
    void executeBackground(HashMap settings, String command) {
        assert command, 'command must be given'
        assert settings != null, 'settings must not be null'
        log.info("Execute a command ($command) with settings ($settings) in background")
        operations.executeBackground(operationSettings + new OperationSettings(settings), command, null)
    }

    @Override
    void executeBackground(HashMap settings, String command, Closure callback) {
        assert command, 'command must be given'
        assert callback, 'callback must be given'
        assert settings != null, 'settings must not be null'
        log.info("Execute a command ($command) with settings ($settings) and callback in background")
        operations.executeBackground(operationSettings + new OperationSettings(settings), command, callback)
    }

    @Override
    void sftp(Closure closure) {
        assert closure, 'closure must be given'
        log.info("Execute a SFTP subsystem")
        operations.sftp(closure)
    }
}
