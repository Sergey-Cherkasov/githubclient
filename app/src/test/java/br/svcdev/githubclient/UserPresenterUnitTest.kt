package br.svcdev.githubclient

import br.svcdev.githubclient.model.entity.GithubUser
import br.svcdev.githubclient.presenter.UserPresenter
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import io.reactivex.rxjava3.core.Scheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import ru.terrakok.cicerone.Router

class UserPresenterUnitTest {

    private lateinit var presenter: UserPresenter

    @Mock
    private lateinit var userEntity: GithubUser

    @Mock
    private lateinit var scheduler: Scheduler

    @Mock
    lateinit var router: Router

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = UserPresenter(scheduler, userEntity)
    }

    @Test
    fun `backpressed test`() {
        presenter.backPressed()
        verify(router, times(0)).exit()
        verifyZeroInteractions(router)
    }
}