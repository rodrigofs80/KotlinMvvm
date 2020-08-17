package br.com.projkotlinexamples

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.projkotlinexamples.http.ItemDataSource
import br.com.projkotlinexamples.ui.model.Image
import br.com.projkotlinexamples.ui.model.Item
import br.com.projkotlinexamples.ui.model.Materia
import br.com.projkotlinexamples.ui.model.Secao
import br.com.projkotlinexamples.vm.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var itensObserver: Observer<List<Materia>>

    @Mock
    private lateinit var msgObserver: Observer<String>

    private val application = Mockito.mock(Application::class.java)

    private lateinit var viewModel: MainViewModel

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when vm get list success then itens is not empty`() {
        // Arrange
        val autores = listOf("Autor 1", "Autor 2")
        val imagens = listOf(Image("Autor Img 1", "Fonte", "Legenda", "https://www.flamengo.com.br"))
        val materias = listOf(
            Materia(autores, true, "Subtitulo", "Texto", "31/02/2020", 1,
                "30/02/2020", Secao("Secao 1", "https://www.youtube.com"), "Tipo 1", "Titluo", "https://www.globo.com", "https://www.globo.com", imagens)
        )
        val item = Item("lista", materias)
        val repositorySuccess = MockRepository(Response.success(listOf(item)))
        viewModel = MainViewModel(repositorySuccess, application)
        viewModel.lista.observeForever(itensObserver)

        // Act
        viewModel.toLoadCoroutines()

        // Assert
        Mockito.verify(itensObserver).onChanged(materias)
    }

    @Test
    fun `when vm get list fail then msg is displayed`() {
        // Arrange
        val repositoryFail = MockRepository(Response.error(500, ResponseBody.create(null, "error")))
        viewModel = MainViewModel(repositoryFail, application)
        viewModel.msg.observeForever(msgObserver)

        // Act
        viewModel.toLoad()

        // Assert
        Mockito.verify(msgObserver).onChanged(application.getString(R.string.failed))
    }

    class MockRepository(private val response: Response<List<Item>>): ItemDataSource { // Mock criado na mao

        override fun listAllObjects(success: (List<Materia>) -> Unit, failure: () -> Unit) {
            if(response.isSuccessful) {
                success(response.body()!!.get(0).conteudos)
            } else {
                failure()
            }
        }

        override suspend fun listAllObjectsCoroutines(): List<Materia> {
            return response.body()!!.get(0).conteudos
        }
    }
}